<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1" import="it.contrader.dto.AttrezzaturaDTO" import="java.util.List" import="it.contrader.dto.UserDTO"
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">
        <link href="/css/vittoriostyle.css" rel="stylesheet">
    </head>
    <body>

    <div class="main container">
            <div class="button-wrapper">

        <%AttrezzaturaDTO a = (AttrezzaturaDTO) request.getSession().getAttribute("attrezzaturaDTO");%>
        <a href="/attrezzatura/getall">Indietro</a>

        <form class="create-form" action="/attrezzatura/update" method="post">
            <input type="hidden" name="mode" value="update">
            <input type="hidden" name="idAttrezzatura" value="<%=a.getIdAttrezzatura()%>">
            <input type="hidden" id="idimpianto" name="idImpianto" value=<%=a.getIdImpianto().getIdImpianto()%> required>
            <h1 id="title">Modifica Attrezzatura</h1>

            <div class="form-group">
                <label for="idattrezzatura">Nome Attrezzatura </label>
                <input type="text" id="idattrezzatura" name="nomeAttrezzatura" value=<%=a.getIdAttrezzatura()%> required>
            </div>


            <div class="form-group">
                <label for="descrizione">Descrizione </label>
                <input type="text" id="descrizione" name="descrizioneAttrezzatura" value=<%=a.getDescrizione()%> required>
            </div>

            <div class="form-group">
                 <label for="prezzo">Prezzo </label>
                 <input type="number" id="prezzo" name="prezzoAttrezzatura" value=<%=a.getPrezzo()%> required>
            </div>




            <div class="button-wrapper">

                <button type="submit" >Effettua Modifica</button>
            </div>
        </form>
    </div>
    </body>
</html>