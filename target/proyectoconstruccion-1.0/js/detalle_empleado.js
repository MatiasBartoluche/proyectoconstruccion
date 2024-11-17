$(document).ready(function(){
    var legajo = localStorage.getItem('detalleEmpleado');
    
    detalleEmpleado(legajo);
});

function detalleEmpleado(legajo){
    console.log(legajo);
    
    $.ajax({
        url: '/proyectoconstruccion/SvDetalleEmpleado', // URL del servlet
        type: 'GET',
        data: {detalleLegajo: legajo},
        dataType: 'json',
        success: function (response) {
            console.log(response);
        },
        error: function (xhr, status, error) {
            console.error('Error al obtener empleados:', error);
        }
    });
}