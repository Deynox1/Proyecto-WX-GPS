package co.edu.uco.app.data.dao.azuresql;

import static co.edu.uco.crosscutting.util.text.UtilText.SPACE;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.edu.uco.app.crosscutting.exception.AppException;
import co.edu.uco.app.data.dao.CompanyDAO;
import co.edu.uco.app.data.dao.connection.ConnectionSQL;
import co.edu.uco.app.dto.CompanyDTO;
import co.edu.uco.crosscutting.util.numeric.UtilNumeric;
import co.edu.uco.crosscutting.util.object.UtilObject;
import co.edu.uco.crosscutting.util.text.UtilText;

public class CompanyPostgreSqlDAO extends ConnectionSQL implements CompanyDAO {

	protected CompanyPostgreSqlDAO(Connection connection) {
		super(connection);
	}
	
	public static CompanyDAO build(Connection connection) {
		return new CompanyPostgreSqlDAO(connection);
	}

	@Override
	public void create(CompanyDTO company) {
		String sql = "INSERT INTO company(name, location, customer) VALUES (?,?,?)";
		
		try(PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			
			preparedStatement.setString(1, company.getName());
			preparedStatement.setString(2, company.getLocation());
			preparedStatement.setInt(3, company.getCustomer().getId());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException exception) {
			
			throw AppException.buildTechnicalDataException("There was a problem trying to create the new company on  PostgreSQL Server", exception);
			
		} catch (Exception exception) {
			
			throw AppException.buildTechnicalDataException("An unexpected problem has ocurred trying to create the new company on PostgreSQL Server", exception);
			
		}
		
	}

	@Override
	public void update(CompanyDTO company) {
		String sql = "UPDATE company SET name = ? WHERE id = ?";
		
		try(PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {	
			
			preparedStatement.setString(1, company.getName());
			preparedStatement.setInt(2, company.getId());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException exception) {
			
			throw AppException.buildTechnicalDataException("There was a problem trying to update the company on PostgreSQL Server", exception);
			
		} catch (Exception exception) {
			
			throw AppException.buildTechnicalDataException("An unexpected problem has ocurred trying to update the company on PostgreSQL Server", exception);
			
		}
		
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM company WHERE id = ?";
		
		try(PreparedStatement preparedStatement = getConnection().prepareStatement(sql)) {
			
			preparedStatement.setInt(1, id);
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException exception) {
			
			throw AppException.buildTechnicalDataException("There was a problem trying to delete company on PostgreSQL Server", exception);
			
		} catch (Exception exception) {
			
			throw AppException.buildTechnicalDataException("An unexpected problem has ocurred trying to delete company on PostgreSQL Server", exception);
			
		}
		
	}

	@Override
	public List<CompanyDTO> find(CompanyDTO company) {
		
		boolean setWhere = true;
		List<Object> parameters = new ArrayList<>();
		List<CompanyDTO> results = new ArrayList<CompanyDTO>();
		
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT id, name").append(SPACE);
		sb.append("FROM company").append(SPACE);
		
		if(!UtilObject.getUtilObject().isNull(company)) {
			
			if(UtilNumeric.getUtilNumeric().isGreaterThan(company.getId(), 0)) {
				
				sb.append("WHERE id = ? ");
				parameters.add(company.getId());
				setWhere = false;
				
			}
			
			if (!UtilText.isEmpty(company.getName())) {
				sb.append(setWhere ? "WHERE " : "AND ");
				sb.append("name = ?");
				parameters.add(UtilText.trim(company.getName()));
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
		throw AppException.buildTechnicalDataException("There was a problem trying to retrive the companies on PostgreSQL Server", exception);		
	} catch (Exception exception) {	
		throw AppException.buildTechnicalDataException("An unexpected problem has ocurred trying to retrive the companies on PostgreSQL Server", exception);	
	}
	
	return results;	
	
  }
	
	private List<CompanyDTO> executeQuery(PreparedStatement preparedStatement) {

		List<CompanyDTO> results = new ArrayList<>();

		try (ResultSet resultSet = preparedStatement.executeQuery()) {

			results = assembleResults(resultSet);

		} catch (SQLException exception) {

			throw AppException.buildTechnicalDataException(
					"There was a problem trying to execute the query for recover company registry on PostgreSQL server",
					exception);

		} catch (Exception exception) {

			throw AppException.buildTechnicalDataException(
					"There was an unexpected problem trying to execute the query for recover company registry on PostgreSQL server",
					exception);

		}

		return results;

	}
	
	  private List<CompanyDTO> assembleResults(ResultSet resultSet) {
		  List<CompanyDTO> results = new ArrayList<>();
		  
		  try {
		      while(resultSet.next()) {
		    	  results.add(assembleDTO(resultSet));		  
		      }
		} catch (AppException exception) {
			throw exception;
	    } catch (SQLException exception) {
			
			throw AppException.buildTechnicalDataException("There was a problem trying to recover the companies on Azure SQL Server", exception);
			
		} catch (Exception exception) {
			
			throw AppException.buildTechnicalDataException("An unexpected problem has ocurred trying to update the companies on Azure SQL Server", exception);		
		}
		  
		  return results;
	  }
  
	  private CompanyDTO assembleDTO(ResultSet resultSet) {
		  
		  CompanyDTO dto = new CompanyDTO();
		  
		try {
			  dto.setId(resultSet.getInt("id"));
			  dto.setName(resultSet.getString("name"));
			  dto.setLocation(resultSet.getString("location"));
			  dto.getCustomer().setId(resultSet.getInt("customer"));
	    } catch (SQLException exception) {
			
			throw AppException.buildTechnicalDataException("There was a problem trying to assemble the companies on Azure SQL Server", exception);
			
		} catch (Exception exception) {
			
			throw AppException.buildTechnicalDataException("An unexpected problem has ocurred trying to assemble the company on Azure SQL Server", exception);		
		}
		
		return dto;
	  }
	
}