/*Catalogo de errores y mensajes*/

var m_0001 = "Hubo un error al iniciar la Aplicacion";
var m_0002 = "Hubo un error con la conexion de Internet";
var m_0003 = "Ocurrio un error en el ingreso";
var m_0004 = "Esta seguro que desea salir de SPED MOVIL?";

//This is an event that fires when the user presses the device back button
/*document.addEventListener("deviceready", onDeviceReady, false);

function onDeviceReady() {
    document.addEventListener("backbutton", backKeyDown, true);
}*/

    salirApp = function () {
        var cFirm = confirm(m_0004);
        if (cFirm == true) {
            navigator.app.exitApp();
        }
    }
        
    // This method shows you how to use variable args 
    doAlert = function () {
        var args = arguments;
        var str = "doAlert, argCount: " + args.length + ", arguments:";
    
        for (x = 0;x < args.length;x++) {
            if (x > 0) {
                str += ", ";
            }
            str += arguments[x];
        }
        alert(str);
    };
    
    /**
     * Metodo para mostrar alertas
     * */
    mostrarMensaje = function () {
        var idMsj = arguments[0];
        if(idMsj.substring(0,2) == "m_"){
            eval("alert("+idMsj+");");
        }else{
            alert(idMsj);
        } 
    };