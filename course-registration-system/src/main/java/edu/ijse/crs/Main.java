package edu.ijse.crs;

import edu.ijse.crs.dto.UserDTO;
import edu.ijse.crs.service.ServiceFactory;
import edu.ijse.crs.service.ServiceFactory.ServiceTypes;
import edu.ijse.crs.service.custom.UserService;
import edu.ijse.crs.entity.UserEntity.Role;

public class Main {
    public static void main(String[] args) throws Exception {
        UserService userService= (UserService) ServiceFactory.getInstance().getService(ServiceTypes.USER);
        // UserDTO userDTO=new UserDTO("A001", "Admin1", Role.ADMIN, null);
        // try {
        //      userService.saveUser(userDTO);
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }


        UserDTO loginUserDTO=new UserDTO("A001", "Admin1");
        try {
            Role role = userService.getRole(loginUserDTO);
            if (role==null) {
                System.out.println("Invalid login");
            }
            System.out.println(role);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}