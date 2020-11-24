/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.app.manoel.table;

import br.app.manoel.dao.Musica;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author manoel
 */
public class MyTable extends AbstractTableModel implements ITableModel{
    
    @Override
    public String getColumnName(int linha){
        return MyTable.colunas[linha]; 
    }
    
    @Override
    public int getRowCount() {
        //To change body of generated methods, choose Tools | Templates.
        return MyTable.lista.size();
    }

    @Override
    public int getColumnCount() {
        //To change body of generated methods, choose Tools | Templates.
        return MyTable.colunas.length;
    }

    @Override
    public Object getValueAt(int linhas, int colunas) {
        //To change body of generated methods, choose Tools | Templates.
        switch(colunas)
        {
            case 0:
                return MyTable.lista.get(linhas).getArtista();
            case 1:
                return MyTable.lista.get(linhas).getTitulo();
            case 2:
                return MyTable.lista.get(linhas).getAlbum();
            case 3:
                return MyTable.lista.get(linhas).getAno();
            case 4:
                return MyTable.lista.get(linhas).getUrl();
        }
        return null;
    }
    
    public void removerLinhaDaTabela(int index)
    {
        MyTable.lista.remove(index);
        this.fireTableDataChanged();
    }
    
    /**
     * Método responsável por limparTabela a tabela.
     */
    public void limparTabela()
    {
        MyTable.lista.clear();
        this.fireTableDataChanged();
    }
    
    /**
     *
     * @param musica
     */
    public void adicionarNovoElementoNaLinhaDaTabela(Musica musica)
    {
        MyTable.lista.add(musica);
        this.fireTableDataChanged();
    }
    
    /**
     *
     * @param index
     * @param musica
     */
    public void adicionarNovaLinhaNaTabela(int index, Musica musica)
    {
        MyTable.lista.add(index, musica);
        this.fireTableDataChanged();
    }
    
    /**
     * Método que atualiza as informações da tabela recebendo com paramentro uma lista.
     * @param lista - a lista contendo os dados.
     */
    public void atualizarListaTabela(ArrayList <Musica> lista)
    {
        MyTable.lista.clear();
        MyTable.lista.addAll(lista);
        this.fireTableDataChanged();
    }
    
    
}
