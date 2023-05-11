package app.classes.hibernatr;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static app.сontroller.RestControllers.LOGGER;

@Repository
public class ResponseDaoImpl implements ResponseDao {

    public ResponseDaoImpl(NamedParameterJdbcTemplate template) {
        this.template = template;
    }
    NamedParameterJdbcTemplate template;

    @Override
    public List<Response> findAll() {
        List<Response> response = new ArrayList<>();
        try {
            response =  template.query("select * from responses", new ResponseRowMapper());
        }
        catch (DataAccessException e) {
            LOGGER.error("Данные не добавлены: {}", e.getMessage());
        }

    return response;

    }

    @Override
    public Response findOption(String method, String optionId) {

        String query = "SELECT * FROM responses WHERE abonent = :abonent and namemethod=:namemethod";
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("abonent", optionId)
                .addValue("namemethod", method);

        return template.queryForObject(
                query, param, new ResponseRowMapper());
    }


    @Override
    public void insertEmployee(Response emp) {
        emp.setResponse(emp.getResponse().replace("\\", ""));
        final String sql = "insert into responses(id, namemethod , abonent,response) values(:id,:namemethod,:abonent,:response)";

        var all = template.query("select * from responses order by 1 desc", new ResponseRowMapper());
        var el = all.get(0).id;

        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", el+1)
                .addValue("namemethod", emp.getMethodname())
                .addValue("abonent", emp.getAbonent())
                .addValue("response", emp.getResponse());
        try {
            template.update(sql, param, holder);
        }
        catch (DataAccessException e) {
            LOGGER.error("Данные не добавлены: {}", e.getMessage());
        }

    }


    @Override
    public void deleteEmployee(String emp, String method) {
        final String sql = "delete from responses where abonent=:abonent and namemethod=:namemethod";


        Map<String,Object> map=new HashMap<String,Object>();
        map.put("abonent", emp);
        map.put("namemethod", method);

        template.execute(sql,map,new PreparedStatementCallback<Object>() {
                @Override
                public Object doInPreparedStatement(PreparedStatement ps)
                        throws SQLException, DataAccessException {
                    return ps.executeUpdate();
                }
            });




    }

}
