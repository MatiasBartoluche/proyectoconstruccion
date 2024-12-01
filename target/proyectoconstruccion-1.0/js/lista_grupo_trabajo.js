$(document).ready(function(){
    console.log('grupos');
    
    buscarGrupos('grupos');
});

function buscarGrupos(mensaje){
    $.ajax({
        url: '/proyectoconstruccion/SvGrupoTrabajo', // URL del servlet
        type: 'GET',
        data: {mensaje: mensaje},
        dataType: 'json',
        success: function (response) {
            console.log(response);
        },
        error: function (xhr, status, error) {
            console.error("Error:", error);
        }
    });
}