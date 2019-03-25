package trialpetpal.demo.users;


import trialpetpal.demo.users.models.GoogleUser;
import trialpetpal.demo.users.models.Organisation;
import trialpetpal.demo.users.models.ParentUser;
import trialpetpal.demo.users.models.PrivateUser;

public enum UserType {

  Private {

    public ParentUser createUser() {
      return new PrivateUser();
    }
  },

  Google{
    public ParentUser createUser(){
      return new GoogleUser();
  }},

  Org {
    public ParentUser createUser() {
      return new Organisation();
    }
  };

  public ParentUser createUser() {
    return null;
  }

}
