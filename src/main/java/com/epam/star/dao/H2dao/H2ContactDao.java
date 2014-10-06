package com.epam.star.dao.H2dao;

import com.epam.star.dao.ContactDao;
import com.epam.star.entity.AbstractEntity;
import com.epam.star.entity.Contact;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class H2ContactDao extends AbstractH2Dao implements ContactDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);
    private static final String ADD_CONTACT = "INSERT INTO CONTACTS VALUES (?, ?, ?)";
    private static final String DELETE_CONTACT = "DELETE FROM CONTACTS WHERE id = ?";

    protected H2ContactDao(Connection conn, DaoManager daoManager) {
        super(conn, daoManager);
    }

    @Override
    public List<Contact> getContacts() throws DaoException {
        List<Contact> result = new ArrayList<>();

        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement("SELECT * FROM CONTACTS");
            resultSet = prstm.executeQuery();
            while (resultSet.next()) {
                result.add(getContactFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return result;
    }

    @Override
    public Contact findById(int ID) throws DaoException {
        String sql = "select * from contacts where id = " + "'" + ID + "'";
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, resultSet);
        }
        return getContactFromResultSet(resultSet);
    }

    @Override
    public String insert(Contact contact) throws DaoException {
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
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, null);
        }
        return status;
    }

    @Override
    public String deleteElement(int ID) throws DaoException {
        String status = "Contact do not deleted";

        PreparedStatement prstm = null;

        try {
            prstm = conn.prepareStatement(DELETE_CONTACT);
            prstm.setString(1, String.valueOf(ID));
            prstm.execute();
            status = "Contact deleted successfully ";
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            closeStatement(prstm, null);
        }
        return status;
    }

    @Override
    public String updateElement(Contact contact) throws DaoException {
        return null;
    }

    public Contact getContactFromResultSet(ResultSet resultSet) throws DaoException {
        Contact contact = new Contact();
        try {
            contact.setId(resultSet.getInt("id"));
            contact.setTelephone(resultSet.getString("telephone"));
            contact.setOwner(resultSet.getString("owner"));
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return contact;
    }

    private void closeStatement(PreparedStatement prstm, ResultSet resultSet) {
        if (prstm != null) {
            try {
                prstm.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }

        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DaoException(e);
            }
        }
    }

    @Override
    public AbstractEntity getEntityFromResultSet(ResultSet resultSet) throws DaoException {
        return null;
    }

    @Override
    public int getRecordsCount() {
        return 0;
    }

    @Override
    public Map<String, String> getParametersMap() {
        return null;
    }

    @Override
    protected String getFindByParameters() {
        return null;
    }
}
