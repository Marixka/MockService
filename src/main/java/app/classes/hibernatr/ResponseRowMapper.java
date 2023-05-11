package app.classes.hibernatr;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResponseRowMapper implements RowMapper<Response> {

    @Override
    public Response mapRow(ResultSet rs, int arg1) throws SQLException {
        Response emp = new Response();
        emp.setId(rs.getInt("id"));
        emp.setMethodname(rs.getString("namemethod"));
        emp.setAbonent(rs.getString("abonent"));
        emp.setResponse(rs.getString("response"));

        return emp;
    }


}
