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
            var contadorObrero = 0;
            var contadorOficina = 0;
            var contdorSubcontratista = 0;
            for (indice = 0; indice < response.length; indice++) {
                if(response[indice].contrato.descripcion === 'Oficina'){
                    insertarEmpleado(response[indice], '#listaOficina', contadorOficina);
                    contadorOficina = contadorOficina +1;
                }
                else if(response[indice].contrato.descripcion === 'Obrero'){
                    insertarEmpleado(response[indice], '#listaObreros', contadorObrero);
                    contadorObrero = contadorObrero +1;
                }
                else if(response[indice].contrato.descripcion === 'Subcontratista'){
                    insertarEmpleado(response[indice], '#listaSubcontratados', contdorSubcontratista);
                    contdorSubcontratista = contdorSubcontratista +1;
                }
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

function insertarEmpleado(empleado, idLista, tipoClase){
    console.log(empleado);
    var clase = '';

    
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
                                    '<span class="btnIcono btnDetalleEmpleado" id="'+empleado.id_empleado+'" data-idEmpleado="'+empleado.id_empleado+'">'+
                                        '<i class="bx bx-search-alt icon"></i>'+
                                    '</span>'+
                                //'</a>'+
                            '</div>'+
                       '</div>');
}

function detalleEmpleado(){
    $('#homeListaEmpleados').on('click', '.btnDetalleEmpleado', function () {
        const idEmpleado = $(this).data('idempleado'); // Obtener el legajo del botón
        console.log(idEmpleado);
        localStorage.setItem('detalleEmpleado', idEmpleado);
        window.location.href = "/proyectoconstruccion/vistas/sistemas/detalle_empleado.jsp";
    });
}