package com.angelalign.userstorage.dao;

import com.angelalign.userstorage.entity.UserEntity;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private final DataSource dataSource;
    private  String site;

    public UserDao(DataSource dataSource,String site) {
        this.dataSource = dataSource;
        this.site = site;
    }

    // Method to get a user by account ID
    public UserEntity getUserById(Integer accountId) {
        String sql = "SELECT * FROM opm_rbac_account WHERE accountId = ? LIMIT 1";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToUserEntity(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to get a user by username
    public UserEntity getUserByUsername(String username) {
        String sql = "SELECT * FROM opm_rbac_account WHERE username = ?  LIMIT 1";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToUserEntity(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to get a user by email
    public UserEntity getUserByEmail(String email) {
        String sql = "SELECT * FROM opm_rbac_account WHERE username = ? LIMIT 1";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapRowToUserEntity(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Method to get the count of users
    public int getUserCount() {
        String sql = "SELECT COUNT(*) FROM opm_rbac_account";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Method to get all users
    public List<UserEntity> getAllUsers() {
        List<UserEntity> users = new ArrayList<>();
        String sql = "SELECT * FROM opm_rbac_account";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                users.add(mapRowToUserEntity(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Method to search for users with a search term
    public List<UserEntity> searchForUser(String search) {
        List<UserEntity> users = new ArrayList<>();
        String sql = "SELECT * FROM opm_rbac_account WHERE username LIKE ? ";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            String searchPattern = "%" + search + "%";
            stmt.setString(1, searchPattern);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    users.add(mapRowToUserEntity(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Helper method to map a ResultSet row to a UserEntity
    private UserEntity mapRowToUserEntity(ResultSet rs) throws SQLException {
        UserEntity user  = new UserEntity();
        user.setAccountId(rs.getInt("accountId"));
        user.setUsername(rs.getString("username"));
        user.setPassword(rs.getString("password"));
        user.setUserId(rs.getInt("userId"));
        user.setSite(site);
        return user;

    }
}
