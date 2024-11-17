$(document).ready(function(){
    
    toggleListas('#btnListaObreros', '#listaObreros', 'obreros');
    toggleListas('#btnListaAdministrativos', '#listaOficina', 'administrativos');
    toggleListas('#btnListaSubcontratados', '#listaSubcontratados', 'subcontratados');
    
    buscarEmpleados();
    
    detalleEmpleado();
});

function buscarEmpleados(){
    $.ajax({
        url: '/proyectoconstruccion/SvEmpleados', // URL del servlet
        type: 'GET',
        dataType: 'json',
        success: function (response) {
            
            for (indice = 0; indice < response.length; indice++) {
                insertarEmpleado(response[indice], response[indice].contrato.descripcion, indice);
            }
        },
        error: function (xhr, status, error) {
            console.error('Error al obtener empleados:', error);
        }
    });
}

function toggleListas(idBoton, idLista, texto){
    $(idBoton).on('click', function () {
        const $contenido = $(idLista); // Seleccionar el div
        $contenido.toggle(300, function () {
            // Cambiar el texto del botón después de mostrar/ocultar
            var mostrar ='';
            var ocultar ='';
            
            if(texto === 'obreros'){
                mostrar = 'Mostrar lista de obreros';
                ocultar = 'Ocultar loista de obreros'; 
            }
            else if(texto === 'administrativos'){
                mostrar = 'Mostrar lista de administrativos';
                ocultar = 'Ocultar loista de administrativos'; 
            }
            else if(texto === 'subcontratados'){
                mostrar = 'Mostrar lista de subcontratados';
                ocultar = 'Ocultar loista de subcontratados'; 
            }
            
            const textoBoton = $contenido.is(':visible') ? ocultar : mostrar;
            $(idBoton).text(textoBoton);
        });
    });
}

function insertarEmpleado(empleado, tipoContrato, tipoClase){
    console.log(empleado);
    var idLista = '';
    var clase = '';
    
    // defino en donde se insertara cada empleado
    if(tipoContrato === "Oficina"){
        idLista = '#listaOficina';
        console.log('insertar en oficina');
    }
    else if(tipoContrato === 'Obrero'){
        idLista = '#listaObreros';
        console.log('insertar en obreros');
    }
    else if(tipoContrato === 'Subcontratista'){
        idLista = '#listaSubcontratados';
        console.log('insertar en subcontratados');
    }
    
    // defino una clase "par" o "impar" dependiendo de la variable "tipoClase"
    if(tipoClase/2 === 0){
        console.log('empleado par');
        clase = 'impar';
    }
    else{
        console.log('empleado impar');
        clase = 'par';
    }
    
    $(idLista).append('<div class="empleado '+clase+'">'+
                            '<p>'+empleado.legajo+'</p>'+
                            '<p>'+empleado.apellidos+', '+empleado.nombres+'</p>'+
                            '<p>'+empleado.cuil+'</p>'+
                            '<p>'+empleado.jerarquia.descripcion+'</p>'+
                            '<p>'+empleado.fecha_ingreso+'</p>'+
                            '<div class="botonesEmpleado">'+
                                //'<a href="./detalle_empleado.jsp">'+
                                    '<span class="btnIcono btnDetalleEmpleado" id="'+empleado.legajo+'" data-legajo="'+empleado.legajo+'">'+
                                        '<i class="bx bx-search-alt icon"></i>'+
                                    '</span>'+
                                //'</a>'+
                            '</div>'+
                       '</div>');
}

function detalleEmpleado(){
    $('#homeListaEmpleados').on('click', '.btnDetalleEmpleado', function () {
        const legajo = $(this).data('legajo'); // Obtener el legajo del botón
        console.log('Detalles del empleado con legajo: ');
        console.log(legajo);
        // Aquí puedes agregar la lógica para manejar el evento
    });
}