function gotopage(){
    console.log("brava bea");
    var pages = {};

    pages['1'] = "https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi01/";
    pages['2'] = "https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi02/";
    pages['3'] = "https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi03/";
    pages['4'] = "https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi04/";
    pages['5'] = "https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi05/";
    pages['6'] = "https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi06/";
    pages['7'] = "https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi07/";
    pages['8'] = "https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi08/";
    pages['9'] = "https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi09/";
    pages['10'] = "https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi10/";
    pages['11'] = "https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi11/";
    pages['12'] = "https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi12/";
    pages['13'] = "https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi13/";
    pages['14'] = "https://museocambellotti.cittadifondazione.it/contenuti-lifi/contenutolifi14/";

    requiredId = document.getElementById("lamp_code_id").value;
    museumId = document.getElementById("museum_code_id").value;
 console.log(requiredId);
    var result = pages[requiredId] === undefined;

    if(museumId == "MCLT"){
    
        if(result){
            //alert("LiFi Zone non esistente");

            var iframe = document.createElement("IFRAME");
            iframe.setAttribute("src", 'data:text/plain,');
            document.documentElement.appendChild(iframe);
            window.frames[0].window.alert('LiFi Zone non esistente');
            iframe.parentNode.removeChild(iframe);

            var x = document.getElementById("mostra-errore");
            x.style.display = "block";


            return;
        }

        location.href = pages[requiredId];
    }else{
        var iframe = document.createElement("IFRAME");
        iframe.setAttribute("src", 'data:text/plain,');
        document.documentElement.appendChild(iframe);
        window.frames[0].window.alert('Codice Sede sconosciuto');
        iframe.parentNode.removeChild(iframe);
        var x = document.getElementById("mostra-errore");
        x.style.display = "block";
    }

}
