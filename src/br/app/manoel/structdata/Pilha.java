/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.app.manoel.structdata;

/**
 *
 * @author manoel
 */
public final class Pilha {
   
   private final int tamanho;
   private int posicao;
   private final int vetor[];
   
   public Pilha(int tamanho)
   {
       this.tamanho = tamanho;
       this.posicao = -1;
       int[] res = new int[tamanho];
       this.vetor = res;
       for(int i= 0;i < tamanho; i++)
       {
           vetor[i] = i;
       }
       criar();
   }
   
   public void criar()
   {
       this.posicao = -1;
   }
   
   public int sizePilha()
   {
       return this.tamanho;
   }
   
   public int getPosicao()
   {
       return this.posicao;
   }
   
   public boolean inserir()
   {
       if(this.posicao == tamanho-1)
       {
           return true;
       } else {
           this.posicao += 1;
           return false;
       }
   }
   public boolean remover()
   {
       if(this.posicao == 0)
       {
           return true;
       }else{
           this.posicao -=1;
           return false;
       }
   }
   
   
}
