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
        
        console.log('click busqueda');
        
        var buscarLegajo = $('#inputLegajo').val();
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
                    console.error('Error al obtener el legajo ingresado:', error);
                }
            });     
    });
}
//recibo la respuesta listaRoles e inserto en la pestania desplegable