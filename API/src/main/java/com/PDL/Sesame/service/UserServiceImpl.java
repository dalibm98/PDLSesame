package com.PDL.Sesame.service;

import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl  {

    /* implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;


    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }






    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User getUserById(Long id) {
        Optional<User> user = userDao.findById(id);
        if(user.isPresent()) {
            return user.get();
        }
        else {
            throw new ResourceNotFoundException("User", "id", id);
        }
    }


    @Override
    public User updateUser(Long id, User user) {
        User existingUser = userDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        existingUser.setEmail(user.getEmail());
        existingUser.setNom(user.getNom());
        existingUser.setPrenom(user.getPrenom());
        existingUser.setRole(user.getRole());
        return userDao.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        userDao.delete(user);
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication instanceof OAuth2AuthenticationToken) {
            OAuth2User principal = (OAuth2User) authentication.getPrincipal();
            String email = principal.getAttribute("email");

            return userDao.findByEmail(email)
                    .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
        }
        return null;
    }










    @Override
    public List<User> getUsersByRole(RoleEnum roleEnum) {
        Role role = roleDao.findByNomRole(roleEnum);
        return userDao.findByRole(role);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userDao.findByEmail(email);


    }







     */


}
