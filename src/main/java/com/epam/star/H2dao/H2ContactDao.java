package com.epam.star.H2dao;

import com.epam.star.dao.ContactDao;
import com.epam.star.entity.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class H2ContactDao extends AbstractH2Dao implements ContactDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);
    private static final String ADD_CONTACT = "INSERT INTO CONTACTS VALUES (?, ?, ?)";
    private static final String DELETE_CONTACT = "DELETE FROM CONTACTS WHERE id = ?";
    private Connection conn;

    public H2ContactDao(Connection conn) {
        this.conn = conn;
    }
    @Override
    public List<Contact> getContacts() {
        List<Contact> result = new ArrayList<>();

        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM CONTACTS");
            ResultSetMetaData resultSetMD = resultSet.getMetaData();
            while (resultSet.next()){
                result.add(getContactFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) { /* ignored */}
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) { /* ignored */}
            }
        }
        return result;
    }

    @Override
    public Contact getElement(int ID) {
        String sql = "select * from contacts where id = " + "'" + ID + "'";
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (prstm != null) {
                try {
                    prstm.close();
                } catch (SQLException e) { /* ignored */}
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) { /* ignored */}
            }
        }
        return getContactFromResultSet(resultSet);
    }

    @Override
    public String insert(Contact contact) {
        String status = "Contact do not added";

        PreparedStatement prstm = null;

        try {
            prstm = conn.prepareStatement(ADD_CONTACT);
            prstm.setString(1, null);
            prstm.setString(2, contact.getTelephone());
            prstm.setString(3, contact.getOwner());

            prstm.execute();
            status = "Contact added successfully";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (prstm != null) {
                try {
                    prstm.close();
                } catch (SQLException e) { /* ignored */}
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) { /* ignored */}
            }
        }
        return status;
    }

    @Override
    public String deleteElement(int ID) {
        String status = "Contact do not deleted";

        PreparedStatement prstm = null;

        try {
            prstm = conn.prepareStatement(DELETE_CONTACT);
            prstm.setString(1, String.valueOf(ID));
            prstm.execute();
            status = "Contact deleted successfully ";
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (prstm != null) {
                try {
                    prstm.close();
                } catch (SQLException e) { /* ignored */}
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) { /* ignored */}
            }
        }
        return status;
    }

    @Override
    public String updateElement(int ID) {
        return null;
    }

    public Contact getContactFromResultSet(ResultSet resultSet) {
        Contact contact = new Contact();
        try {
            contact.setId(resultSet.getInt("id"));
            contact.setTelephone(resultSet.getString("telephone"));
            contact.setOwner(resultSet.getString("owner"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contact;
    }
}
