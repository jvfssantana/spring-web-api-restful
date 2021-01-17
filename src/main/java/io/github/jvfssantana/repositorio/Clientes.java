package io.github.jvfssantana.repositorio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import io.github.jvfssantana.entity.Cliente;

@Repository
public class Clientes {
	
	private static String INSERT_SQL = "INSERT INTO CLIENTE (NOME) VALUES (?)";
	private static String SELECT_SQL = "SELECT * FROM CLIENTE"; 
	private static String UPDATE_SQL = "UPDATE CLIENTE SET NOME = (?) WHERE ID = (?)";
	private static String DELETE_SQL = "DELETE FROM CLIENTE WHERE ID = (?)";
	private static String SELECT_NOME = "SELECT * FROM CLIENTE WHERE NOME LIKE (?) ";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public Cliente salvarCliente(Cliente cliente) {
		jdbcTemplate.update(INSERT_SQL, new Object[]{cliente.getNome()});
		return cliente;
	}
	
	public List<Cliente> obterTodos() {
		return jdbcTemplate.query(SELECT_SQL, obterClienteMapper());
	}
	
	public Cliente atualizar(Cliente cliente) {
		jdbcTemplate.update(UPDATE_SQL, new Object[]{cliente.getNome(), cliente.getId()});
		return cliente;
	}
	
	public void deletar(Cliente cliente) {
		deletar(cliente.getId());
	}
	
	public void deletar(Integer id) {
		jdbcTemplate.update(DELETE_SQL, new Object[]{id});
	}
	
	public List<Cliente> buscarPorNome(String nome) {
		return jdbcTemplate.query(SELECT_NOME, new Object[]{"%" + nome + "%"}, obterClienteMapper());
	}
	
	private RowMapper<Cliente> obterClienteMapper() {
		return new RowMapper<Cliente>() {
			@Override
			public Cliente mapRow(ResultSet resultSet, int i) throws SQLException {
				Integer id = resultSet.getInt("id");
				String nome = resultSet.getString("nome");
				return new Cliente(id, nome);
			}
		};
	}
}