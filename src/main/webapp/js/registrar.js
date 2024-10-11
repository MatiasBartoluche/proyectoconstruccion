$(document).ready(function(){
    
    cargarRoles();
    
    buscarEmpleado();
});

function cargarRoles(){
$.ajax({
        url: 'SvRegistrar', // URL del servlet
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            // 'data' es un array de objetos Usuario en formato JSON
            console.log(data);
            // Aquí puedes iterar y mostrar los datos en tu página
            
            $.each(data, function(i, item){
                $('#rol').append('<option value="'+item.id_rol+'">'+item.descripcion+'</option>');
            });
            
        },
        error: function(xhr, status, error) {
            console.error('Error al obtener la lista de roles:', error);
        }
    });
}

function buscarEmpleado(){
    
    $('#btnBuscarEmpleado').click(function(){
        
        $('#resultadoBusquedaEmpleado').empty();
        var buscarLegajo = $('#inputLegajo').val();
        
        if(buscarLegajo === ''){
            console.log("no hay legajo");
            $('#resultadoBusquedaEmpleado').append('<p>Ingrese un numero de legajo</p>');
        }
        else{
            console.log(buscarLegajo);
            $.ajax({
                    url: 'SvResultadoBuscarLegajo', // URL del servlet
                    type: 'GET',
                    data: {legajo: buscarLegajo},
                    dataType: 'json',
                    success: function(response) {
                        // 'data' es un array de objetos Usuario en formato JSON
                        console.log(response);
                    },
                    error: function(xhr, status, error) {
                        //console.error('Error al obtener el legajo ingresado:', error);
                        console.log('error al obtener el empleado');
                        $('#resultadoBusquedaEmpleado').append('<p>No se encontro un empleado con ese legajo</p>');
                    }
                });  
        }
    });
}
//recibo la respuesta listaRoles e inserto en la pestania desplegable