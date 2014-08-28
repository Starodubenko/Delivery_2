package com.epam.star.H2dao;

import com.epam.star.dao.ClientDao;
import com.epam.star.entity.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class H2ClientDao extends AbstractH2Dao implements ClientDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(H2ClientDao.class);
    private static final String ADD_CLIENT = "INSERT INTO  USERS VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE_CLIENT = "DELETE FROM clients WHERE id = ?";
    private Connection conn;

    public H2ClientDao(Connection conn) {
        this.conn = conn;
    }

    @Override
    public Client findByLogin(String login) {
        String sql = "select * from clients where login = " + "'" + login + "'";

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
        return getClientFromResultSet(resultSet);
    }

    @Override
    public Client findByName(String name) {
        String sql = "select * from clients where firstname = " + "'" + name + "'";

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
        return getClientFromResultSet(resultSet);
    }

    @Override
    public Client findBySurnameName(String surName) {
        String sql = "select * from clients where surname = " + "'" + surName + "'";
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
        return getClientFromResultSet(resultSet);
    }

    @Override
    public Client findByAddress(String address) {
        String sql = "select * from clients where address= " + "'" + address + "'";
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
        return getClientFromResultSet(resultSet);
    }

    @Override
    public Client findByTelephone(String telephone) {
        String sql = "select * from clients where telephone = " + "'" + telephone + "'";
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
        return getClientFromResultSet(resultSet);
    }

    @Override
    public Client findByMobilephone(String mobilephone) {
        String sql = "select * from clients where surname = " + "'" + mobilephone + "'";
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
        return getClientFromResultSet(resultSet);
    }

    @Override
    public Client findByCredentials(String login, String password) {
        String sql = "SELECT *" +
                " FROM USERS" +
                " inner join POSITIONS" +
                " on users.POSITION_ID = positions.id" +
                " where POSITION_ID = 11 and LOGIN = " + "'" + login + "'" + " and PASSWORD = " + "'" + password + "'";
        PreparedStatement prstm = null;
        ResultSet resultSet = null;
        try {
            prstm = conn.prepareStatement(sql);
            resultSet = prstm.executeQuery();
            if (resultSet.next())
                return getClientFromResultSet(resultSet);
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
        return null;
    }

    @Override
    public List<Client> getAllClients() {
        List<Client> result = new ArrayList<>();

        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * " +
                    "FROM USERS inner join POSITIONS " +
                    "on users.POSITION_ID = positions.id where POSITION_ID = 11");
            ResultSetMetaData resultSetMD = resultSet.getMetaData();
            while (resultSet.next()){
                result.add(getClientFromResultSet(resultSet));
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
    public Client getElement(int ID) {
        String sql = "select * from users where id = " + ID;
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
        return getClientFromResultSet(resultSet);
    }

    @Override
    public String insert(Client client) {

        String status = "Client do not added";

        PreparedStatement prstm = null;

            try {
                prstm = conn.prepareStatement(ADD_CLIENT);
                prstm.setString(1, null);
                prstm.setString(2, client.getLogin());
                prstm.setString(3, client.getPassword());
                prstm.setString(4, client.getFirstName());
                prstm.setString(5, client.getLastName());
                prstm.setString(6, client.getMiddleName());
                prstm.setString(7, client.getAddress());
                prstm.setString(8, client.getTelephone());
                prstm.setString(9, client.getMobilephone());
                prstm.setString(10, null);
                prstm.setString(11, null);
                prstm.setString(12, null);
                prstm.setString(13, null);
                prstm.setString(14, "11");
                prstm.setBigDecimal(15, new BigDecimal(0));
                prstm.execute();
                status = "Client added successfully";
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
        String status = "Client do not deleted";

        PreparedStatement prstm = null;

        try {
            prstm = conn.prepareStatement(DELETE_CLIENT);
            prstm.setString(1, String.valueOf(ID));
            prstm.execute();
            status = "Client successfully deleted";
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

    private Client getClientFromResultSet(ResultSet resultSet) {
        Client client = new Client();
        try {
            client.setId(resultSet.getInt("id"));
            client.setLogin(resultSet.getString("login"));
            client.setPassword(resultSet.getString("password"));
            client.setFirstName(resultSet.getString("firstname"));
            client.setLastName(resultSet.getString("lastname"));
            client.setMiddleName(resultSet.getString("middlename"));
            client.setAddress(resultSet.getString("address"));
            client.setTelephone(resultSet.getString("telephone"));
            client.setMobilephone(resultSet.getString("mobilephone"));
            client.setRole(resultSet.getString("position_name"));
            client.setVirtualBalance(new BigDecimal(resultSet.getInt("virtual_balance")));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return client;
    }
}
