package co.edu.uco.app.data.dao.azuresql;

import static co.edu.uco.crosscutting.util.text.UtilText.SPACE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.app.crosscutting.exception.AppException;
import co.edu.uco.app.data.dao.VehicleTypeDAO;
import co.edu.uco.app.data.dao.connection.ConnectionSQL;
import co.edu.uco.app.dto.VehicleTypeDTO;
import co.edu.uco.crosscutting.util.numeric.UtilNumeric;
import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.crosscutting.util.text.UtilText;

public class VehicleTypePostgreSqlDAO extends ConnectionSQL implements VehicleTypeDAO {

	private VehicleTypePostgreSqlDAO(Connection connection) {
		super(connection);
	}
	
	public static VehicleTypeDAO build(Connection connection) {
		return new VehicleTypePostgreSqlDAO(connection);
	}

	@Override
	public void create(VehicleTypeDTO vehicleType) {
		String sql = "INSERT INTO vehicletype(type) VALUES (?)";
		
		try(PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			
			preparedStatement.setString(1, vehicleType.getType());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException exception) {
			
			throw AppException.buildTechnicalDataException("There was a problem trying to create the new vehicle type on PostgreSQL Server", exception);
			
		} catch (Exception exception) {
			
			throw AppException.buildTechnicalDataException("An unexpected problem has ocurred trying to create the new vehicle type on PostgreSQL Server", exception);
			
		}
		
	}

	@Override
	public void update(VehicleTypeDTO vehicleType) {
		String sql = "UPDATE vehicletype SET type = ? WHERE id = ?";
		
		try(PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {	
			
			preparedStatement.setString(1, vehicleType.getType());
			preparedStatement.setInt(2, vehicleType.getId());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException exception) {
			
			throw AppException.buildTechnicalDataException("There was a problem trying to update the new vehicle type on Azure SQL Server", exception);
			
		} catch (Exception exception) {
			
			throw AppException.buildTechnicalDataException("An unexpected problem has ocurred trying to update the new vehicle type on Azure SQL Server", exception);
			
		}
		
	}
	@Override
	public void delete(int id) {
		String sql = "DELETE FROM vehicleType WHERE id = ?";
		
		try(PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			
			preparedStatement.setInt(1, id);
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException exception) {
			
			throw AppException.buildTechnicalDataException("There was a problem trying to delete vehicle type on Azure SQL Server", exception);
			
		} catch (Exception exception) {
			
			throw AppException.buildTechnicalDataException("An unexpected problem has ocurred trying to delete vehicle type on Azure SQL Server", exception);
			
		}
		
	}
		

	@Override
	public List<VehicleTypeDTO> find(VehicleTypeDTO vehicleType) {
		boolean setWhere = true;
		List<Object> parameters = new ArrayList<>();
		List<VehicleTypeDTO> results = new ArrayList<VehicleTypeDTO>();
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT id, type").append(SPACE);
		sb.append("FROM vehicletype").append(SPACE);
		
		if(!UtilObject.getUtilObject().isNull(vehicleType)) {
			
			if(UtilNumeric.getUtilNumeric().isGreaterThan(vehicleType.getId(), 0)) {
				
				sb.append("WHERE id = ? ");
				parameters.add(vehicleType.getId());
				setWhere = false;
				
			}
			
			if (!UtilText.isEmpty(vehicleType.getType())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("type = ?");
				parameters.add(UtilText.trim(vehicleType.getType()));
		}	
	}
		
	sb.append("ORDER BY name ASC");
	
	try(PreparedStatement preparedStatement = getConnection().prepareStatement(sb.toString())) {	
		
		for  (int index = 0; index < parameters.size(); index++) {
			preparedStatement.setObject(index + 1, parameters.get(index));			
		}
		
		results = executeQuery(preparedStatement);
		
	} catch (AppException exception) {
		throw exception;
	} catch (SQLException exception) {		
		throw AppException.buildTechnicalDataException("There was a problem trying to retrive the vehicle types on Azure SQL Server", exception);		
	} catch (Exception exception) {	
		throw AppException.buildTechnicalDataException("An unexpected problem has ocurred trying to retrive the vehicle types on Azure SQL Server", exception);	
	}
	
	return results;	
	
  }
	
	private List<VehicleTypeDTO> executeQuery(PreparedStatement preparedStatement) {

		List<VehicleTypeDTO> results = new ArrayList<>();

		try (ResultSet resultSet = preparedStatement.executeQuery()) {

			results = assembleResults(resultSet);

		} catch (SQLException exception) {

			throw AppException.buildTechnicalDataException(
					"There was a problem trying to execute the query for recover vehicle registry on PostgreSQL server",
					exception);

		} catch (Exception exception) {

			throw AppException.buildTechnicalDataException(
					"There was an unexpected problem trying to execute the query for recover vehicle registry on PostgreSQL server",
					exception);

		}

		return results;

	}
	
	private List<VehicleTypeDTO> assembleResults(ResultSet resultSet) {
		List<VehicleTypeDTO> results = new ArrayList<>();

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
					"There was an unexpected problem trying to recover the vehicle types registry on PostgreSQL server", exception);

		}

		return results;

	}
  
	  private VehicleTypeDTO assembleDTO(ResultSet resultSet) {
		  
		  VehicleTypeDTO dto = new VehicleTypeDTO();
		  
		try {
			  dto.setId(resultSet.getInt("id"));
			  dto.setType(resultSet.getString("type"));	  
			  
	    } catch (SQLException exception) {
			
			throw AppException.buildTechnicalDataException("There was a problem trying to assemble the vehicle type on Azure SQL Server", exception);
			
		} catch (Exception exception) {
			
			throw AppException.buildTechnicalDataException("An unexpected problem has ocurred trying to assemble the vehicle type on Azure SQL Server", exception);		
		}
		
		return dto;
	  }
	
}
