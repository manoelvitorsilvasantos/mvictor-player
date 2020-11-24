/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.app.manoel.database;

import br.app.manoel.dao.Musica;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author manoel
 */
public class Database {
    
    
    private static Database instancia = null;
    private Connection conexao = null;
    private PreparedStatement ps = null;
    private int clientes = 0;

    private Database()
    {
	try{
			
            String driver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://localhost:3306/musica?zeroDateTimeBehavior=convertToNull";
            String user = "mvictor";
            String pass = "65564747";
	
            if(user.length() != 0)
            {
                conexao = DriverManager.getConnection(url, user, pass);
            }
            else{
		conexao = DriverManager.getConnection(url);
            }
            System.out.println("DB[conexao OK]");
	}
	catch(SQLException ex)
	{
            System.out.println(ex);
	}
    }

    public static Database getInstancia(){
        
	if(instancia == null){ 
            instancia = new Database();
        }
        return instancia;
    }	

    public Connection getConexao(){
        
	if(conexao == null){
            throw new RuntimeException("connection==null");
	}
	clientes++;
	System.out.println("DB[conexao cliente]");
	return conexao;
    }

    public void desligar()
    {
        System.out.println("DB[ desligar clientes]");
        clientes--;
        
        if(clientes > 0) {
            return;
        }
        
        try{
            conexao.close();
            instancia = null;
            conexao =  null;
            System.out.println("DB[conexao fechada]");
        }
        catch(SQLException ex)
        {
            System.out.println(ex);
        }
    }
    
    /**
     *
     * @param musica
     * @return
     */
    public boolean inserir(Musica musica)
    {
        try{ 
            PreparedStatement stmt = Database.getInstancia().getConexao().prepareStatement("INSERT INTO tbl_musica(titulo, artista, album, ano, url) VALUES(?,?,?,?,?)");
            stmt.setString(1, musica.getTitulo());
            stmt.setString(2, musica.getArtista());
            stmt.setString(3, musica.getAlbum());
            stmt.setInt(4, musica.getAno());
            stmt.setString(5, musica.getUrl());
            stmt.execute();
            return true;
        }
        catch(SQLException ex)
        {
            return false;
        }
        
    }
    
    public String getTitulo(int posicao) throws SQLException
    {   String titulo = null;
        String sql = "SELECT titulo FROM tbl_musica WHERE id = (?)";
        Statement stmt = Database.getInstancia().getConexao().createStatement();
        ResultSet resultado = stmt.executeQuery(sql);
        if(resultado.next())
        {
            titulo = resultado.getString("titulo");
        }
        return titulo;
    }
    
    public List<Musica> findAll() throws SQLException
    {
        String consulta = "SELECT titulo, artista, album, ano, url FROM tbl_musica ORDER BY artista";
        Connection conn = Database.getInstancia().getConexao();
        ps = conexao.prepareStatement(consulta);
        ResultSet resultado = ps.executeQuery();
        
        List<Musica> lista = new ArrayList<>();
 
        while(resultado.next())
        {
            Musica musica = new Musica();
            musica.setTitulo(resultado.getString("titulo"));
            musica.setArtista(resultado.getString("artista"));
            musica.setAlbum(resultado.getString("album"));
            musica.setAno(resultado.getInt("ano"));
            musica.setUrl(resultado.getString("url"));
            lista.add(musica);
        }
        return lista;
    }
    
}
