package main.java.com.excilys.projet.java.cdb.persistence.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import main.java.com.excilys.projet.java.cdb.mapper.CompanyMapper;
import main.java.com.excilys.projet.java.cdb.models.Company;
import main.java.com.excilys.projet.java.cdb.models.Page;
import main.java.com.excilys.projet.java.cdb.persistence.Connect;

public class CompanyDAO extends Dao<Company> {
    private static CompanyDAO companyDAO;
    private static Logger logger = LoggerFactory.getLogger(CompanyDAO.class);
    private Connection connect = Connect.getInstance();

    /**
     * Private constructor of CompanyDAO.
     */
    private CompanyDAO() {
    }

    /**
     * Instance of the singleton CompanyDAO.
     * @return the instance of CompanyDAO
     */
    public static synchronized CompanyDAO getInstance() {
        if (companyDAO == null) {
            companyDAO = new CompanyDAO();
        }
        return companyDAO;
    }

    @Override
    public List<Company> getAll() {
        List<Company> companyList = new ArrayList<Company>();

        try (PreparedStatement statement = connect.prepareStatement("SELECT id, name FROM company ORDER BY id")) {
            ResultSet resultSet = statement.executeQuery();
			while(resultSet.next()) {
				companyList.add(CompanyMapper.getCompany(resultSet));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			
		}
        return companyList;
    }

    /**
     * Get all the companies on a given page.
     * @param page
     * @return list of the companies
     */
    public List<Company> getAllByPage(Page page) {
        List<Company> companyList = new ArrayList<Company>();

        if (page.getCurrentPage() > 0) {
            try (PreparedStatement statement = connect.prepareStatement("SELECT id, name FROM company ORDER BY id LIMIT ? OFFSET ?")) {
                statement.setInt(1, page.getMaxLine());
                statement.setInt(2, page.getPageFirstLine());

                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Company company = CompanyMapper.getCompany(resultSet);
                    companyList.add(company);
                }
            } catch (SQLException e) {
                logger.error("sql error when listing companies by page");
            }
        }

        return companyList;
    }

    @Override
    public Optional<Company> findById(Long id) {
        Optional<Company> result = Optional.empty();
        if (id != null) {
            try (PreparedStatement statement = this.connect.prepareStatement("SELECT id, name FROM company WHERE id = ?")) {
                statement.setLong(1, id);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    result = Optional.ofNullable(CompanyMapper.getCompany(resultSet));
                }
            } catch (SQLException e) {
                logger.error("error sql");
            }
        }
        return result;
    }
}
