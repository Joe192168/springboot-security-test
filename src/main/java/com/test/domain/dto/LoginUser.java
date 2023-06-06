    package com.test.domain.dto;

    import com.test.domain.entity.Authority;
    import com.test.domain.entity.User;
    import lombok.Data;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;
    import org.springframework.security.core.userdetails.UserDetails;

    import javax.jws.soap.SOAPBinding;
    import java.util.ArrayList;
    import java.util.Collection;
    import java.util.List;

    /**
     * @ClassName: LoginUser
     * @Author : xqh
     * @Date :2023/5/31
     * @Description: TODO
     */
    @Data
    public class LoginUser implements UserDetails {

        private User user;

        public LoginUser(User user) {
            this.user = user;
        }

        /**
         * 返回用户的权限集合。
         * @return
         */
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            for (Authority authority : user.getAuthorityList()) {
                // 角色必须以`ROLE_`开头，数据库中没有，则在这里加
                authorities.add(new SimpleGrantedAuthority("ROLE_"+authority.getAuthority()));
            }
            return authorities;
        }

        @Override
        public String getPassword() {
            return user.getPassword();
        }

        @Override
        public String getUsername() {
            return user.getUsername();
        }

        /**
         * 账号是否失效，true:账号有效，false账号失效。
         * @return
         */
        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        /**
         * 账号是否被锁，true:账号没被锁，可用；false：账号被锁，不可用
         * @return
         */
        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        /**
         * 账号认证是否过期，true:没过期，可用；false：过期，不可用
         * @return
         */
        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        /**
         * 账号是否可用，true:可用，false:不可用
         * @return
         */
        @Override
        public boolean isEnabled() {
            return true;
        }
    }
