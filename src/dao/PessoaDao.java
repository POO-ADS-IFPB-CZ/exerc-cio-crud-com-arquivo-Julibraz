package dao;

import model.Pessoa;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

//DAO (Data Access Object)
public class PessoaDao {
    private File arquivo;

    public PessoaDao() {
        arquivo = new File("pessoas.ser");
        //pessoas.ser sera criado caso ainda não exista
        if(!arquivo.exists()){
            try{
                arquivo.createNewFile();
                System.out.println("Arquivo criado com sucesso.");
            }catch (IOException e){
                System.out.println("Falha ao criar arquivo");
            }
        }
    }

    public Set<Pessoa> getPessoas(){
        if(arquivo.length()>0){
            try{
                FileInputStream inputStream = new FileInputStream(arquivo);
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                Set<Pessoa> pessoas = (Set<Pessoa>) objectInputStream.readObject();
                return pessoas;
                //se o arquivo não for encontrado
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo não encontrado");
                //Se ocorrer um erro durante a leitura do arquivo
            } catch (IOException e) {
                System.out.println("Falha ao ler arquivo");
                //Se a classe não puder ser encontrada no momento da desserialização
            } catch (ClassNotFoundException e) {
                System.out.println("Falha ao ler arquivo");
            }
        }
        //Se o arquivo estiver vazio, sera criado um novo conjunto
        return new HashSet<>();
    }

    public boolean salvarPessoa(Pessoa pessoa){
        //copnjunto de pessoas armazenadas no arquivo
        Set<Pessoa> pessoas = getPessoas();
        if(pessoas.add(pessoa)){
            try {
                //salva o conjunto atualizado no arquivo
                FileOutputStream outputStream = new FileOutputStream(arquivo);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(pessoas);
                return true;
            }catch(FileNotFoundException e){
                System.out.println("Arquivo não encontrado");

            }catch(IOException e){
                System.out.println("Falha ao salvar arquivo");
            }
        }
        return false;
    }

    //deletar uma pessoa pelo email
    public boolean deletarPessoa(String email){
        Set<Pessoa> pessoas = getPessoas();
        for(Pessoa pessoa : pessoas){
            if(pessoa.getEmail().equals(email)){
                pessoas.remove(pessoa);
                try {
                    FileOutputStream outputStream = new FileOutputStream(arquivo);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                    objectOutputStream.writeObject(pessoas);
                    return true;
                } catch (FileNotFoundException e) {
                    System.out.println("Arquivo não encontrado");
                } catch (IOException e) {
                    System.out.println("Falha ao salvar arquivo");
                }
            }
        }
        return false;
    }

    //Listar todas as pessoas
    public void listarPessoas(){
        if(getPessoas().isEmpty()){
            System.out.println("\nNenhuma pessoa encontrada\n");
        }else{
            Set<Pessoa> pessoas = getPessoas();
            for(Pessoa pessoa : pessoas){
                System.out.println(pessoa.toString());
            }
        }

    }


}
