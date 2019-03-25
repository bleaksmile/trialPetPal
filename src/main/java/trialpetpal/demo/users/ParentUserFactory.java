package trialpetpal.demo.users;

import trialpetpal.demo.users.models.ParentUser;

public class ParentUserFactory {

  public static ParentUser create(UserType userType) {
    return userType.createUser();
  }
}