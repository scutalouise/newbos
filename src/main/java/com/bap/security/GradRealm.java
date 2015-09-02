package com.bap.security;


public class GradRealm  {
	 

    /*public GradRealm() {
         super();
         //设置认证token的实现类
         setAuthenticationTokenClass(UsernamePasswordToken.class);
         //设置加密算法
         setCredentialsMatcher(new HashedCredentialsMatcher(Sha1Hash.ALGORITHM_NAME));
         
    }
    //授权
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
         String loginName = (String) principalCollection.fromRealm(getName()).iterator().next();
         Staff user = null;// securityApplication.findby(loginName);
         if (null == user) {
              return null;
         } else {
              SimpleAuthorizationInfo result = new SimpleAuthorizationInfo();
              
              result.addRoles(UserRoles.findRoleNamesOf(user));
              for (Role role : UserRoles.findRolesOf(user)) {
                   result.addStringPermissions(role.getPermissions());
              }
              
              return result;

         }
    }

    //认证
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
         UsernamePasswordToken upToken = (UsernamePasswordToken) token;
         Staff user = null; // securityApplication.findby(upToken.getUsername());
         if (user != null) {
              return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
         }
         return null;
    }*/
}
