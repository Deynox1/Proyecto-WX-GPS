package co.edu.uco.app.data.dao.azuresql;

import static co.edu.uco.crosscutting.util.text.UtilText.SPACE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.app.crosscutting.exception.AppException;
import co.edu.uco.app.data.dao.DispositiveDAO;
import co.edu.uco.app.data.dao.connection.ConnectionSQL;
import co.edu.uco.app.dto.DispositiveDTO;
import co.edu.uco.crosscutting.util.numeric.UtilNumeric;
import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.crosscutting.util.text.UtilText;

public class DispositivePostgreSqlDAO extends ConnectionSQL implements DispositiveDAO {

	private DispositivePostgreSqlDAO(Connection connection) {
		super(connection);
	}
	
	public static DispositiveDAO build(Connection connection) {
		return new DispositivePostgreSqlDAO(connection);
	}

	@Override
	public void create(DispositiveDTO dispositive) {
		String sql = "INSERT INTO Dispositive(id, serialNumber, coordinates) VALUES (?, ?, ?)";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setInt(1, dispositive.getId());
			preparedStatement.setString(2, dispositive.getSerialNumber());
			preparedStatement.setString(3, dispositive.getCoordinates());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			throw AppException.buildTechnicalDataException(
					"There was a problem trying to create a new dispositive registry on sql server", exception);
		} catch (Exception exception) {

			throw AppException.buildTechnicalDataException(
					"There was an unexpected problem trying to create a new dispositive registry on sql server", exception);
		}
	}

	@Override
	public void update(DispositiveDTO dispositive) {
		String sql = "UPDATE Dispositive SET serialNumber = ? WHERE id=?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, dispositive.getSerialNumber());
			preparedStatement.setInt(2, dispositive.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw AppException.buildTechnicalDataException(
					"There was a problem trying to update a new dispositive registry on sql server", exception);

		} catch (Exception exception) {

			throw AppException.buildTechnicalDataException(
					"There was an unexpected problem trying to update dispositive registry on sql server", exception);

		}

	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM dispositive WHERE id=?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw AppException.buildTechnicalDataException(
					"There was a problem trying to delete a dispositive registry on sql server", exception);

		} catch (Exception exception) {

			throw AppException.buildTechnicalDataException(
					"There was an unexpected problem trying to delete a dispositive registry on sql server", exception);

		}

	}

	@Override
	public List<DispositiveDTO> find(DispositiveDTO dispositive) {

		boolean setWhere = true;
		List<Object> parameters = new ArrayList<>();
		List<DispositiveDTO> results = new ArrayList<>();

		StringBuilder sb = new StringBuilder(SPACE);
		sb.append("SELECT id, name").append(SPACE);
		sb.append("FROM dispositive");

		if (!UtilObject.getUtilObject().isNull(dispositive)) {

			if (UtilNumeric.getUtilNumeric().isGreaterThan(dispositive.getId(), 0)) {
				sb.append("WHERE").append(SPACE);
				sb.append("id = ? ");
				parameters.add(dispositive.getId());
				setWhere = false;

			}

			if (!UtilText.isEmpty(dispositive.getSerialNumber())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("Serial Number = ? ");
				parameters.add(UtilText.trim(dispositive.getSerialNumber()));
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
					"There was a problem trying to find dispositive registry on sql server", exception);

		} catch (Exception exception) {

			throw AppException.buildTechnicalDataException(
					"There was an unexpected problem trying to find an dispositive registry on sql server", exception);

		}

		return results;

	}

	private List<DispositiveDTO> executeQuery(PreparedStatement preparedStatement) {

		List<DispositiveDTO> results = new ArrayList<>();

		try (ResultSet resultSet = preparedStatement.executeQuery()) {

			results = assembleResults(resultSet);

		} catch (SQLException exception) {

			throw AppException.buildTechnicalDataException(
					"There was a problem trying to execute the query for recover dispositive registry on PostgreSQL server",
					exception);

		} catch (Exception exception) {

			throw AppException.buildTechnicalDataException(
					"There was an unexpected problem trying to execute the query for recover dispositive registry on PostgreSQL server",
					exception);

		}

		return results;

	}

	private List<DispositiveDTO> assembleResults(ResultSet resultSet) {
		List<DispositiveDTO> results = new ArrayList<>();

		try {
			while (resultSet.next()) {

				results.add(assembleDTO(resultSet));

			}

		} catch (AppException exception) {

			throw exception;

		} catch (SQLException exception) {

			throw AppException.buildTechnicalDataException("There was a problem trying to recover the dispositives",
					exception);

		} catch (Exception exception) {

			throw AppException.buildTechnicalDataException(
					"There was an unexpected problem trying to recover the dispositives registry on sql server", exception);

		}

		return results;

	}

	private DispositiveDTO assembleDTO(ResultSet resultSet) {

		DispositiveDTO dto = new DispositiveDTO();

		try {

			dto.setId(resultSet.getInt("id"));
			dto.setSerialNumber(resultSet.getString("serialNumber"));
			dto.setCoordinates(resultSet.getString("coordinates"));

		} catch (SQLException exception) {

			throw AppException.buildTechnicalDataException("There was a problem trying to assemble the dispositives",
					exception);

		} catch (Exception exception) {

			throw AppException.buildTechnicalDataException(
					"There was an unexpected problem trying to assemble the dispositives on sql server", exception);

		}

		return dto;	
    }
}