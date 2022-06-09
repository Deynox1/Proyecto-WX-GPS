package co.edu.uco.app.data.dao.azuresql;

import static co.edu.uco.crosscutting.util.text.UtilText.SPACE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.app.crosscutting.exception.AppException;
import co.edu.uco.app.data.dao.VehicleDAO;
import co.edu.uco.app.data.dao.connection.ConnectionSQL;
import co.edu.uco.app.dto.VehicleDTO;
import co.edu.uco.crosscutting.util.numeric.UtilNumeric;
import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.crosscutting.util.text.UtilText;

public class VehiclePostgreSqlDAO extends ConnectionSQL implements VehicleDAO {

	private VehiclePostgreSqlDAO(Connection connection) {
		super(connection);
	}
	
	public static VehicleDAO build(Connection connection) {
		return new VehiclePostgreSqlDAO(connection);
	}

	@Override
	public void create(VehicleDTO vehicle) {
		String sql = "INSERT INTO Vehicle(vehicle, vehicletype, dispositive) VALUES(?,?,?)";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, vehicle.getVehicle());
			preparedStatement.setInt(2, vehicle.getVehicleType().getId());
			preparedStatement.setInt(3, vehicle.getDispositive().getId());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw AppException.buildTechnicalDataException(
					"There was a problem trying to create a vehicle type registry on sql server", exception);

		} catch (Exception exception) {

			throw AppException.buildTechnicalDataException(
					"There was an unexpected problem trying to create a new vehicle type registry on sql server", exception);

		}

	}

	@Override
	public void update(VehicleDTO Vehicle) {
		String sql = "UPDATE Vehicle SET vehicle = ? WHERE id=?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setString(1, Vehicle.getVehicle());
			preparedStatement.setInt(2, Vehicle.getId());
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw AppException.buildTechnicalDataException(
					"There was a problem trying to update vehicle type registry on sql server", exception);

		} catch (Exception exception) {

			throw AppException.buildTechnicalDataException(
					"There was an unexpected problem trying to update vehicle type registry on sql server", exception);

		}

	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM Vehicle WHERE id=?";

		try (PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {

			throw AppException.buildTechnicalDataException(
					"There was a problem trying to delete a vehicle type registry on sql server", exception);

		} catch (Exception exception) {

			throw AppException.buildTechnicalDataException(
					"There was an unexpected problem trying to delete a vehicle type registry on sql server", exception);

		}

	}

	@Override
	public List<VehicleDTO> find(VehicleDTO Vehicle) {

		boolean setWhere = true;
		List<Object> parameters = new ArrayList<>();
		List<VehicleDTO> results = new ArrayList<VehicleDTO>();

		StringBuilder sb = new StringBuilder(SPACE);
		sb.append("Select id, vehicle, vehicletype, dispositive").append(SPACE);
		sb.append("From Vehicle ");

		if (!UtilObject.getUtilObject().isNull(Vehicle)) {

			if (UtilNumeric.getUtilNumeric().isGreaterThan(Vehicle.getId(), 0)) {
				sb.append("WHERE").append(SPACE);
				sb.append("id = ? ");
				parameters.add(Vehicle.getId());
				setWhere = false;

			}

			if (!UtilText.isEmpty(Vehicle.getVehicle())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("type = ? ");
				parameters.add(UtilText.trim(Vehicle.getVehicle()));
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
					"There was a problem trying to find a vehicle type registry on sql server", exception);

		} catch (Exception exception) {

			throw AppException.buildTechnicalDataException(
					"There was an unexpected problem trying to find a vehicle type registry on sql server", exception);

		}

		return results;

	}

	private List<VehicleDTO> executeQuery(PreparedStatement preparedStatement) {

		List<VehicleDTO> results = new ArrayList<>();

		try (ResultSet resultSet = preparedStatement.executeQuery()) {

			results = assembleResults(resultSet);

		} catch (SQLException exception) {

			throw AppException.buildTechnicalDataException(
					"There was a problem trying to execute the query for recover vehicle type registry on PostgreSQL server",
					exception);

		} catch (Exception exception) {

			throw AppException.buildTechnicalDataException(
					"There was an unexpected problem trying to execute the query for recover vehicle type registry on PostgreSQL server",
					exception);

		}

		return results;

	}

	private List<VehicleDTO> assembleResults(ResultSet resultSet) {
		List<VehicleDTO> results = new ArrayList<>();

		try {
			while (resultSet.next()) {

				results.add(assembleDTO(resultSet));

			}

		} catch (AppException exception) {

			throw exception;

		} catch (SQLException exception) {

			throw AppException.buildTechnicalDataException("There was a problem trying to recover the vehicle types",
					exception);

		} catch (Exception exception) {

			throw AppException.buildTechnicalDataException(
					"There was an unexpected problem trying to recover the costumers registry on PostgreSQL server", exception);

		}

		return results;

	}

	private VehicleDTO assembleDTO(ResultSet resultSet) {

		VehicleDTO dto = new VehicleDTO();

		try {

			dto.setId(resultSet.getInt("id"));
			dto.setVehicle(resultSet.getString("vehicle"));
			dto.getVehicleType().setId(resultSet.getInt("idtype"));
			dto.getVehicleType().setType(resultSet.getString("idtype"));
			dto.getDispositive().setId(resultSet.getInt("dispositive"));
			dto.getDispositive().setSerialNumber(resultSet.getString("dispositive"));
			dto.getDispositive().setCoordinates(resultSet.getString("dispositive"));

		} catch (SQLException exception) {

			throw AppException.buildTechnicalDataException("There was a problem trying to assemble the vehicle types",
					exception);

		} catch (Exception exception) {

			throw AppException.buildTechnicalDataException(
					"There was an unexpected problem trying to assemble the vehicle types on PostgreSQL server", exception);

		}

		return dto;

	}
}
