    package com.test.domain.dto;

    import com.test.domain.entity.User;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;
    import org.springframework.security.core.userdetails.UserDetails;

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
    @NoArgsConstructor
    public class LoginUser implements UserDetails {

        private static final long serialVersionUID = 1L;

        private User user;

        //权限集合
        private List<String> permissions;

        private List<SimpleGrantedAuthority> authorities;

        public LoginUser(User user, List<String> permissions) {
            this.user = user;
            this.permissions = permissions;
        }

        /**
         * 返回用户的权限集合。
         * @return
         */
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            List<GrantedAuthority>  authorities = new ArrayList<>();
            for (String perm : permissions) {
                authorities.add(new SimpleGrantedAuthority(perm));
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
