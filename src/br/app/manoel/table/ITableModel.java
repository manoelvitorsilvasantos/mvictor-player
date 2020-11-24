/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.app.manoel.table;

import br.app.manoel.dao.Musica;
import java.util.ArrayList;

/**
 *
 * @author manoel
 */
public interface ITableModel {
    public ArrayList<Musica> lista = new ArrayList<>();
    
    public String[] colunas = new String[]
    {
         "Artista", "Título", "Album", "Ano", "url"
    };
    
}
