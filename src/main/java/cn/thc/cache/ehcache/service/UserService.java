package cn.thc.cache.ehcache.service;

import cn.thc.cache.ehcache.vo.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by thc on 2017/3/1
 */
@Service
public class UserService {

    private Set<User> users;

    public UserService() {
        users = new HashSet<User>();
        User user1 = new User(1, "张三");
        User user2 = new User(2, "李四");
        User user3 = new User(3, "王五");
        users.add(user1);
        users.add(user2);
        users.add(user3);
    }

    @Cacheable({"users"})
    public User findUser(User user) {
        return findUserInDB(user.getId());
    }

    @Cacheable(value = "users", condition = "#user.getId() <= 2")
    public User findUserInLimit(User user) {
        return findUserInDB(user.getId());
    }

    @CachePut(value = "users",key = "#user.getId()")
    public void updateUser(User user){
        updateUserInDB(user);
    }

    @CacheEvict(value = "users")
    public void removeUser(User user){
        removeUserInDB(user.getId());
    }

    @CacheEvict(value = "users",allEntries = true)
    public void clear(){
        removeAllInDB();
    }

    /**
     * 模拟查找数据库
     *
     * @param id
     * @return
     */
    private User findUserInDB(int id) {
        for (User user : users) {
            if (id == user.getId()) {
                System.out.println("查找数据库 id = " + id + " 成功");
                return user;
            }
        }
        return null;
    }

    /**
     * 模拟更新数据库
     *
     * @param user
     */
    private void updateUserInDB(User user) {
        for (User u : users) {
            if (user.getId() == u.getId()) {
                System.out.println("更新数据库" + u + " -> " + user);
                u.setName(user.getName());
            }
        }
    }

    /**
     * 模拟删除数据库
     *
     * @param id
     */
    private void removeUserInDB(int id) {
        for (User u : users) {
            if (id == u.getId()) {
                System.out.println("从数据库移除 id = " + id + " 的数据");
                users.remove(u);
                break;
            }
        }
    }

    private void removeAllInDB() {
        users.clear();
    }
}
