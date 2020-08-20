package controle;

import bancodedados.ConfiguracoesBanco;
import java.io.File;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ControleMetodos {

    SimpleDateFormat converteAno = new SimpleDateFormat("yyyy");
    SimpleDateFormat converteData = new SimpleDateFormat("dd/MM/yyyy");
    String filePathConfigBanco = "config.xml";
    
    public String criptografar(String senha) {

        String senhaAux = senha;
        String senhaCriptografada = "";

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte messageDigest[] = md.digest(senhaAux.getBytes("UTF-8"));

            StringBuilder sb = new StringBuilder();

            for (byte b : messageDigest) {
                sb.append(String.format("%02x", 0xFF & b));
            }

            senhaCriptografada = sb.toString();

        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            Logger.getLogger(ControleMetodos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return senhaCriptografada;
    }

    public String nomeMaiusculo(String nome) {

        String nomeMaiusculo = "";

        for (String palavras : nome.toLowerCase().split(" ")) {
            if (!"".equals(palavras)) {
                if (!"".equals(nomeMaiusculo)) {
                    nomeMaiusculo += " ";
                }
                if (palavras.length() > 2) {
                    nomeMaiusculo += palavras.substring(0, 1).toUpperCase() + palavras.substring(1);
                } else {
                    nomeMaiusculo += palavras;
                }
            }
        }

        return nomeMaiusculo;

    }

    public String primeiraLetraMaiuscula(String nome) {

        String nomeMaiusculo = "";

        nomeMaiusculo = nome.trim().substring(0, 1).toUpperCase();
        nomeMaiusculo += nome.trim().substring(1, nome.length());
        return nomeMaiusculo;

    }

    public int idadeAcampante(Date dataNascimento) {

        GregorianCalendar hoje = new GregorianCalendar();
        GregorianCalendar nasc = new GregorianCalendar();

        int anoHoje, mesHoje, diaHoje, anoNasc, mesNasc, diaNasc, idade;

        anoHoje = hoje.get(Calendar.YEAR);
        mesHoje = hoje.get(Calendar.MONTH) + 1;
        diaHoje = hoje.get(Calendar.DAY_OF_MONTH);

        nasc.setTime(dataNascimento);
        anoNasc = nasc.get(Calendar.YEAR);
        mesNasc = nasc.get(Calendar.MONTH) + 1;
        diaNasc = nasc.get(Calendar.DAY_OF_MONTH);

        idade = anoHoje - anoNasc;

        if (mesHoje - mesNasc < 0) {
            idade--;
        } else if (mesHoje == mesNasc) {
            if (diaHoje - diaNasc < 0) {
                idade--;
            }
        }

        return idade;
    }

    public String anoAntes(int ano) {
        String anoAntes = String.valueOf(ano - 1);
        anoAntes += "/01/01";
        return anoAntes;
    }

    public String anoDepois(int ano) {
        String anoDepois = String.valueOf(ano + 1);
        anoDepois += "/01/01";
        return anoDepois;
    }

    public String converteAnoString(Date data) {
        String dataString = converteAno.format(data);
        dataString += "/01/01";
        return dataString;
    }

    public String converteDataString(Date data) {
        return converteData.format(data);
    }
    
    public String mesVencimento(String dataVenda, int numeroParcela){
        
        //---- CONSTRUIR ----\\
        
        return "18/08/2020";
    }

    public String gerarCodigoUsuario(int numero) {

        String num = null;

        if ((numero >= 1000) && (numero < 10000)) {
            num = String.valueOf(numero + 1);
        }
        if ((numero >= 100) && (numero < 1000)) {
            num = "0" + String.valueOf(numero + 1);
        }
        if ((numero >= 9) && (numero < 100)) {
            num = "00" + String.valueOf(numero + 1);
        }
        if (numero < 9) {
            num = "000" + String.valueOf(numero + 1);
        }

        return "USU" + num;

    }
    
    public String gerarCodigoCliente(int numero) {

        String num = null;

        if ((numero >= 1000) && (numero < 10000)) {
            num = String.valueOf(numero + 1);
        }
        if ((numero >= 100) && (numero < 1000)) {
            num = "0" + String.valueOf(numero + 1);
        }
        if ((numero >= 9) && (numero < 100)) {
            num = "00" + String.valueOf(numero + 1);
        }
        if (numero < 9) {
            num = "000" + String.valueOf(numero + 1);
        }

        return "CLI" + num;

    }

    public String gerarCodigoProduto(int numero) {

        String num = null;

        if ((numero >= 1000) && (numero < 10000)) {
            num = String.valueOf(numero + 1);
        }
        if ((numero >= 100) && (numero < 1000)) {
            num = "0" + String.valueOf(numero + 1);
        }
        if ((numero >= 9) && (numero < 100)) {
            num = "00" + String.valueOf(numero + 1);
        }
        if (numero < 9) {
            num = "000" + String.valueOf(numero + 1);
        }

        return "PRO" + num;

    }

    public String gerarNumeroVenda(long numero) {

        String numeroIncrementado = null;

        if ((numero >= 10000000000000L) && (numero < 100000000000000L)) {
            numeroIncrementado = "" + String.valueOf(numero + 1);
        }
        if ((numero >= 1000000000000L) && (numero < 10000000000000L)) {
            numeroIncrementado = "0" + String.valueOf(numero + 1);
        }
        if ((numero >= 100000000000L) && (numero < 1000000000000L)) {
            numeroIncrementado = "00" + String.valueOf(numero + 1);
        }
        if ((numero >= 10000000000L) && (numero < 100000000000L)) {
            numeroIncrementado = "000" + String.valueOf(numero + 1);
        }
        if ((numero >= 1000000000) && (numero < 10000000000L)) {
            numeroIncrementado = "0000" + String.valueOf(numero + 1);
        }
        if ((numero >= 100000000) && (numero < 1000000000)) {
            numeroIncrementado = "00000" + String.valueOf(numero + 1);
        }
        if ((numero >= 10000000) && (numero < 100000000)) {
            numeroIncrementado = "000000" + String.valueOf(numero + 1);
        }
        if ((numero >= 1000000) && (numero < 10000000)) {
            numeroIncrementado = "0000000" + String.valueOf(numero + 1);
        }
        if ((numero >= 100000) && (numero < 1000000)) {
            numeroIncrementado = "00000000" + String.valueOf(numero + 1);
        }
        if ((numero >= 10000) && (numero < 100000)) {
            numeroIncrementado = "000000000" + String.valueOf(numero + 1);
        }
        if ((numero >= 1000) && (numero < 10000)) {
            numeroIncrementado = "0000000000" + String.valueOf(numero + 1);
        }
        if ((numero >= 100) && (numero < 1000)) {
            numeroIncrementado = "00000000000" + String.valueOf(numero + 1);
        }
        if ((numero >= 9) && (numero < 100)) {
            numeroIncrementado = "000000000000" + String.valueOf(numero + 1);
        }
        if (numero < 9) {
            numeroIncrementado = "0000000000000" + String.valueOf(numero + 1);
        }

        return numeroIncrementado;
    }

    public void gravarXML(String usuario, String senha, String banco, String servidor) throws Exception {

        DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = fabrica.newDocumentBuilder();
        Document doc = builder.parse(filePathConfigBanco);

        Node nomeUsuario = doc.getElementsByTagName("usuario").item(0);
        nomeUsuario.setTextContent(usuario);

        Node nomeSenha = doc.getElementsByTagName("senha").item(0);
        nomeSenha.setTextContent(senha);

        Node nomeBanco = doc.getElementsByTagName("banco").item(0);
        nomeBanco.setTextContent(banco);

        Node nomeServidor = doc.getElementsByTagName("servidor").item(0);
        nomeServidor.setTextContent(servidor);

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(filePathConfigBanco));

        transformer.transform(source, result);
    }

    public ConfiguracoesBanco lerXML() throws Exception {

        DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = fabrica.newDocumentBuilder();
        Document document = builder.parse(filePathConfigBanco);

        NodeList usuarioBanco = document.getElementsByTagName("usuario");
        Element usuarioDb = (Element) usuarioBanco.item(0);
        String usuario = usuarioDb.getTextContent();

        NodeList senhaBanco = document.getElementsByTagName("senha");
        Element senhaDb = (Element) senhaBanco.item(0);
        String senha = senhaDb.getTextContent();

        NodeList nomeBanco = document.getElementsByTagName("banco");
        Element nomeDb = (Element) nomeBanco.item(0);
        String banco = nomeDb.getTextContent();

        NodeList servidorBanco = document.getElementsByTagName("servidor");
        Element servidorDb = (Element) servidorBanco.item(0);
        String servidor = servidorDb.getTextContent();

        ConfiguracoesBanco configuracoesBanco = new ConfiguracoesBanco();

        configuracoesBanco.setUsuario(usuario);
        configuracoesBanco.setSenha(senha);
        configuracoesBanco.setBanco(banco);
        configuracoesBanco.setServidor(servidor);

        return configuracoesBanco;
    }
}
