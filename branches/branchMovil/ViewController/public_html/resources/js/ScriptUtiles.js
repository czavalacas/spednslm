/*Catalogo de errores y mensajes*/

var m_0001 = "Hubo un error al iniciar la Aplicacion";
var m_0002 = "Hubo un error con la conexion de Internet";
var m_0003 = "Ocurrio un error en el ingreso";
var m_0004 = "Esta seguro que desea salir de SPED MOVIL?";
var m_0005 = "Asigne valores mayores a 0 a todos los Indicadores";
var m_0006 = "Seleccione un Problema";
var m_0007 = "Escriba un comentario";

//This is an event that fires when the user presses the device back button
document.addEventListener("deviceready", onDeviceReady, false);

function onDeviceReady() {
    document.addEventListener("backbutton", retrocederNativo, true);
}

function retrocederNativo() {
    //adf.mf.api.invokeMethod("mobile.respaldo.Bienvenida.bMain", "retorna", onSuccess, onFail);adf.mf.api.invokeMethod("mobile.respaldo.Evaluacion.bEvaluar", "resetearValores", onSuccess, onFail);
    if ($('#login').length) {
        var cFirm = confirm(m_0004);
        if (cFirm == true) {
            navigator.app.exitApp();
        }
    }else {
        if($('#pageEvaluar').length){
            llamarJava('Evaluacion.bEvaluar','resetearValores');
        }
        if($('#pageConsEvaluacion').length){
            llamarJava('Consultas.bConsultarEvaluacion','accionBack');
        }
        if($('#pageDetallePO').length){
            llamarJava('parteocu.bDetalle_PO','_resetearAlSalir');
        }
        if($('#pageGrafPO').length){
            llamarJava('parteocu.Graficos_PO','_reset');
        }
        if($('#pagebRegistrarPO').length){
            llamarJava('parteocu.bRegistrarPO','resetearValores');
        }
        adf.mf.api.amx.doNavigation('__back');
    }
}

function llamarJava(ruta,metodo){
    adf.mf.api.invokeMethod("mobile.respaldo."+ruta,metodo, onSuccess, onFail);
}

function onSuccess(request, response) {

}

function onFail(request, response) {

}

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
    if (idMsj.substring(0, 2) == "m_") {
        eval("alert(" + idMsj + ");");
    }
    else {
        alert(idMsj);
    }
};

showPopup = function () {
    var element = document.getElementById("btnShowPop");
    customTriggerEvent(element, "touchstart");
    customTriggerEvent(element, "touchend");
}

var customTriggerEvent = function (eventTarget, eventType, triggerExtra) {
    var evt = document.createEvent("HTMLEvents");
    evt.initEvent(eventType, true, true);
    evt.view = window;
    evt.altKey = false;
    evt.ctrlKey = false;
    evt.shiftKey = false;
    evt.metaKey = false;
    evt.keyCode = 0;
    evt.charCode = 'a';
    if (triggerExtra != null)
        evt.triggerExtra = triggerExtra;
    eventTarget.dispatchEvent(evt);
};