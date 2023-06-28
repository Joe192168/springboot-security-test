    package com.test.domain.dto;

    import com.fasterxml.jackson.annotation.JsonIgnore;
    import com.test.domain.entity.TUser;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import lombok.ToString;
    import org.springframework.security.core.GrantedAuthority;
    import org.springframework.security.core.authority.SimpleGrantedAuthority;
    import org.springframework.security.core.userdetails.UserDetails;

    import java.util.Collection;
    import java.util.List;
    import java.util.Set;

    /**
     * UserDetails实现类
     */
    @Data
    @NoArgsConstructor
    @ToString
    public class LoginUser implements UserDetails {

        private static final long serialVersionUID = 1L;

        private TUser TUser;

        /**
         * 用户唯一标识
         */
        private String token;

        /**
         * 登录时间
         */
        private Long loginTime;

        /**
         * 过期时间
         */
        private Long expireTime;

        /**
         * 登录IP地址
         */
        private String ipaddr;

        /**
         * 登录地点
         */
        private String loginLocation;

        /**
         * 浏览器类型
         */
        private String browser;

        /**
         * 操作系统
         */
        private String os;

        /**
         * 权限列表
         */
        private Set<String> permissions;

        //角色集合
        private List<String> roles;

        private List<SimpleGrantedAuthority> authorities;

        public LoginUser(TUser TUser, Set<String> permissions) {
            this.TUser = TUser;
            this.permissions = permissions;
        }

        /**
         * 返回用户的权限集合。
         * @return
         */
        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorities;
        }

        @JsonIgnore
        @Override
        public String getPassword() {
            return TUser.getPassword();
        }

        @Override
        public String getUsername() {
            return TUser.getUsername();
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
