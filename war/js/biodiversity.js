var puntos = [];
    var  ventanas= [];
	var unpunto = null;
	var map = null;
	var center = null;


	$.getJSON("/xochimilcoservlet?opcion=1", {}, function(data){
			$.each(data.places, function(i, item){
					center  = new google.maps.LatLng(item.lat, item.lon);				
			});
		});
		
		

      

	

    function hide(){
    		if(puntos){
    			for(p in puntos){
    				puntos[p].setMap(null);
    			}
    		}
    }



  
  function biodiversity(){
		 var chinampa = 'imgs/chinampa.jpg';
		 var flor = 'imgs/flor.jpg';
//http://pastebin.com/Y4qB4DRg

	$.getJSON("/xochimilcoservlet?opcion=3", {}, function(data){
			$.each(data.places, function(i, item){
				var ic = "imgs/icono-biodiversidad.png";
				var marker = new google.maps.Marker({
					position: new google.maps.LatLng(item.lat,
					 item.lon),
					map: map,
					title: item.nombre,
					icon:ic
				});
				puntos[i] = marker;
				var infowindow = new google.maps.InfoWindow({
					content: "<h3>"+ item.nombre +"</h3><p>"+ 
					item.nombre +"</p>" + '<div>'+
		                     '<img src="' + item.img + '" />'+'</div>'
		                     + "<p><br/>" + item.descripcion + "<br/><br/></p>"
		                    
				});
				ventanas[i] = infowindow;
				google.maps.event.addListener(marker, 'click', function() {
					infowindow.open(map, marker);
				});
			});
		});
	 }
  
 
    
    function showBiodiversity(){
    	hide();
    	biodiversity();
    }