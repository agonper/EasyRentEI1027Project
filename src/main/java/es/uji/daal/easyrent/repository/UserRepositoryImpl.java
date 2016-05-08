package es.uji.daal.easyrent.repository;

import es.uji.daal.easyrent.model.User;
import es.uji.daal.easyrent.utils.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by Alberto on 08/05/2016.
 */
public class UserRepositoryImpl implements UserRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private PasswordEncryptor passwordEncryptor;

    @Transactional(readOnly = true)
    @Override
    public boolean authenticate(String username, String password) {
        User user = (User) em.createQuery("select u from User u where u.username = :username", User.class)
                .setParameter("username", username).getSingleResult();
        return passwordEncryptor.validatePassword(password, user.getPassword());
    }
}
