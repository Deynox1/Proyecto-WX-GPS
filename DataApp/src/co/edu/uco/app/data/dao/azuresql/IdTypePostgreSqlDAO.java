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
import co.edu.uco.app.data.dao.IdTypeDAO;
import co.edu.uco.app.data.dao.connection.ConnectionSQL;
import co.edu.uco.app.dto.IdTypeDTO;

public class IdTypePostgreSqlDAO extends ConnectionSQL implements IdTypeDAO {

	protected IdTypePostgreSqlDAO(Connection connection) {
		super(connection);
	}

	public static IdTypeDAO build(Connection connection) {
		return new IdTypePostgreSqlDAO(connection);
	}

	@Override
	public void create(IdTypeDTO idType) {
		String sql = "INSERT INTO IdType(name) VALUES(?)";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, idType.getName());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw AppException.buildTechnicalDataException(
					"There was a problem trying to create a new id type registry on sql server", exception);

		} catch (Exception exception) {

			throw AppException.buildTechnicalDataException(
					"There was an unexpected problem trying to create a new id type registry on sql server", exception);

		}

	}

	@Override
	public void update(IdTypeDTO idType) {
		String sql = "UPDATE IdType SET name = ? WHERE id=?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, idType.getName());
			preparedStatement.setInt(2, idType.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw AppException.buildTechnicalDataException(
					"There was a problem trying to update a new id type registry on sql server", exception);

		} catch (Exception exception) {

			throw AppException.buildTechnicalDataException(
					"There was an unexpected problem trying to update id type registry on sql server", exception);

		}

	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM IdType WHERE id=?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw AppException.buildTechnicalDataException(
					"There was a problem trying to delete an id type registry on sql server", exception);

		} catch (Exception exception) {

			throw AppException.buildTechnicalDataException(
					"There was an unexpected problem trying to delete an id type registry on sql server", exception);

		}

	}

	@Override
	public List<IdTypeDTO> find(IdTypeDTO idType) {

		boolean setWhere = true;
		List<Object> parameters = new ArrayList<>();
		List<IdTypeDTO> results = new ArrayList<IdTypeDTO>();

		StringBuilder sb = new StringBuilder(SPACE);
		sb.append("Select id, name").append(SPACE);
		sb.append("From IdType ");

		if (!UtilObject.getUtilObject().isNull(idType)) {

			if (UtilNumeric.getUtilNumeric().isGreaterThan(idType.getId(), 0)) {
				sb.append("WHERE").append(SPACE);
				sb.append("id = ? ");
				parameters.add(idType.getId());
				setWhere = false;

			}

			if (!UtilText.isEmpty(idType.getName())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("name = ? ");
				parameters.add(UtilText.trim(idType.getName()));
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
					"There was a problem trying to find id type registry on sql server", exception);

		} catch (Exception exception) {

			throw AppException.buildTechnicalDataException(
					"There was an unexpected problem trying to find an id type registry on sql server", exception);

		}

		return results;

	}

	private List<IdTypeDTO> executeQuery(PreparedStatement preparedStatement) {

		List<IdTypeDTO> results = new ArrayList<>();

		try (ResultSet resultSet = preparedStatement.executeQuery()) {

			results = assembleResults(resultSet);

		} catch (SQLException exception) {

			throw AppException.buildTechnicalDataException(
					"There was a problem trying to execute the query for recover id type registry on sql server",
					exception);

		} catch (Exception exception) {

			throw AppException.buildTechnicalDataException(
					"There was an unexpected problem trying to execute the query for recover id type registry on sql server",
					exception);

		}

		return results;

	}

	private List<IdTypeDTO> assembleResults(ResultSet resultSet) {
		List<IdTypeDTO> results = new ArrayList<>();

		try {
			while (resultSet.next()) {

				results.add(assembleDTO(resultSet));

			}

		} catch (AppException exception) {

			throw exception;

		} catch (SQLException exception) {

			throw AppException.buildTechnicalDataException("There was a problem trying to recover the id types",
					exception);

		} catch (Exception exception) {

			throw AppException.buildTechnicalDataException(
					"There was an unexpected problem trying to recover the id types registry on sql server", exception);

		}

		return results;

	}

	private IdTypeDTO assembleDTO(ResultSet resultSet) {

		IdTypeDTO dto = new IdTypeDTO();

		try {

			dto.setId(resultSet.getInt("id"));
			dto.setName(resultSet.getString("name"));

		} catch (SQLException exception) {

			throw AppException.buildTechnicalDataException("There was a problem trying to assemble the id types",
					exception);

		} catch (Exception exception) {

			throw AppException.buildTechnicalDataException(
					"There was an unexpected problem trying to assemble the id types on sql server", exception);

		}

		return dto;

	}

	@Override
	public List<IdTypeDTO> findAlreadyInUseIdTypes() {
		List<IdTypeDTO> results = new ArrayList<IdTypeDTO>();
		
		String sql = "SELECT ITS.id, ITS.name FROM Student EST INNER JOIN IdType ITS ON EST.idType = ITS.id UNION SELECT IT2.id, ITP.name FROM Professor PROF INNER JOIN IdType ITP ON PROF.idType = ITP.id;";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			results = executeQuery(preparedStatement);
		} catch (SQLException exception) {

			throw AppException.buildTechnicalDataException(
					"There was a problem trying to delete the id type on sql server", exception);

		} catch (Exception exception) {

			throw AppException.buildTechnicalDataException(
					"There was an unexpected problem trying to delete the id type on sql server", exception);

		}
		
		return results;
		
	}
}	

