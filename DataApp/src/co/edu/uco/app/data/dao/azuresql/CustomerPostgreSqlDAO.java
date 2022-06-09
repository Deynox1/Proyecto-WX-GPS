package co.edu.uco.app.data.dao.azuresql;

import static co.edu.uco.crosscutting.util.text.UtilText.SPACE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.crosscutting.util.numeric.UtilNumeric;
import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.crosscutting.util.text.UtilText;
import co.edu.uco.app.crosscutting.exception.AppException;
import co.edu.uco.app.data.dao.CustomerDAO;
import co.edu.uco.app.data.dao.connection.ConnectionSQL;
import co.edu.uco.app.dto.CustomerDTO;

public class CustomerPostgreSqlDAO extends ConnectionSQL implements CustomerDAO {

	protected CustomerPostgreSqlDAO(Connection connection) {
		super(connection);
	}

	public static CustomerDAO build(Connection connection) {
		return new CustomerPostgreSqlDAO(connection);
	}

	@Override
	public void create(CustomerDTO customer) {
		String sql = "INSERT INTO Customer(idnumber, idtype, name, location, dispositive) VALUES(?,?,?,?,?)";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, customer.getIdNumber());
			preparedStatement.setInt(2, customer.getIdType().getId());
			preparedStatement.setString(3, customer.getName());
			preparedStatement.setString(4, customer.getLocation());
			preparedStatement.setInt(5, customer.getDispositive().getId());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw AppException.buildTechnicalDataException(
					"There was a problem trying to create a customer registry on sql server", exception);

		} catch (Exception exception) {

			throw AppException.buildTechnicalDataException(
					"There was an unexpected problem trying to create a new customer registry on sql server", exception);

		}

	}

	@Override
	public void update(CustomerDTO Customer) {
		String sql = "UPDATE Customer SET name = ? WHERE id=?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, Customer.getName());
			preparedStatement.setInt(2, Customer.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw AppException.buildTechnicalDataException(
					"There was a problem trying to update customer registry on sql server", exception);

		} catch (Exception exception) {

			throw AppException.buildTechnicalDataException(
					"There was an unexpected problem trying to update customer registry on sql server", exception);

		}

	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM Customer WHERE id=?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw AppException.buildTechnicalDataException(
					"There was a problem trying to delete a customer registry on sql server", exception);

		} catch (Exception exception) {

			throw AppException.buildTechnicalDataException(
					"There was an unexpected problem trying to delete a customer registry on sql server", exception);

		}

	}

	@Override
	public List<CustomerDTO> find(CustomerDTO Customer) {

		boolean setWhere = true;
		List<Object> parameters = new ArrayList<>();
		List<CustomerDTO> results = new ArrayList<CustomerDTO>();

		StringBuilder sb = new StringBuilder(SPACE);
		sb.append("Select id, idnumber, idtype, name, location, dispositive").append(SPACE);
		sb.append("From Customer ");

		if (!UtilObject.getUtilObject().isNull(Customer)) {

			if (UtilNumeric.getUtilNumeric().isGreaterThan(Customer.getId(), 0)) {
				sb.append("WHERE").append(SPACE);
				sb.append("id = ? ");
				parameters.add(Customer.getId());
				setWhere = false;

			}

			if (!UtilText.isEmpty(Customer.getIdNumber())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("idnumber = ? ");
				parameters.add(UtilText.trim(Customer.getIdNumber()));
			}

		}

		sb.append("ORDER BY name ASC");

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sb.toString())) {

			for (int index = 0; index < parameters.size(); index++) {
				preparedStatement.setObject(index + 1, parameters.get(index));
			}

			results = executeQuery(preparedStatement);

		} catch (AppException exception) {
			throw exception;

		} catch (SQLException exception) {

			throw AppException.buildTechnicalDataException(
					"There was a problem trying to find a customer registry on sql server", exception);

		} catch (Exception exception) {

			throw AppException.buildTechnicalDataException(
					"There was an unexpected problem trying to find a customer registry on sql server", exception);

		}

		return results;

	}

	private List<CustomerDTO> executeQuery(PreparedStatement preparedStatement) {

		List<CustomerDTO> results = new ArrayList<>();

		try (ResultSet resultSet = preparedStatement.executeQuery()) {

			results = assembleResults(resultSet);

		} catch (SQLException exception) {

			throw AppException.buildTechnicalDataException(
					"There was a problem trying to execute the query for recover customer registry on PostgreSQL server",
					exception);

		} catch (Exception exception) {

			throw AppException.buildTechnicalDataException(
					"There was an unexpected problem trying to execute the query for recover customer registry on PostgreSQL server",
					exception);

		}

		return results;

	}

	private List<CustomerDTO> assembleResults(ResultSet resultSet) {
		List<CustomerDTO> results = new ArrayList<>();

		try {
			while (resultSet.next()) {

				results.add(assembleDTO(resultSet));

			}

		} catch (AppException exception) {

			throw exception;

		} catch (SQLException exception) {

			throw AppException.buildTechnicalDataException("There was a problem trying to recover the customers",
					exception);

		} catch (Exception exception) {

			throw AppException.buildTechnicalDataException(
					"There was an unexpected problem trying to recover the costumers registry on PostgreSQL server", exception);

		}

		return results;

	}

	private CustomerDTO assembleDTO(ResultSet resultSet) {

		CustomerDTO dto = new CustomerDTO();

		try {

			dto.setId(resultSet.getInt("id"));
			dto.setIdNumber(resultSet.getString("idnumber"));
			dto.getIdType().setId(resultSet.getInt("idtype"));
			dto.setName(resultSet.getString("name"));
			dto.getIdType().setName(resultSet.getString("idtype"));
			dto.setLocation(resultSet.getString("location"));
			dto.getDispositive().setId(resultSet.getInt("dispositive"));
			dto.getDispositive().setSerialNumber(resultSet.getString("dispositive"));
			dto.getDispositive().setCoordinates(resultSet.getString("dispositive"));

		} catch (SQLException exception) {

			throw AppException.buildTechnicalDataException("There was a problem trying to assemble the customers",
					exception);

		} catch (Exception exception) {

			throw AppException.buildTechnicalDataException(
					"There was an unexpected problem trying to assemble the customers on PostgreSQL server", exception);

		}

		return dto;

	}
}