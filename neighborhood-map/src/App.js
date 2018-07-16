import React, { Component } from 'react';
import './App.css';
import {locationInfo} from './locationInfo'
import fetchJsonp from 'fetch-jsonp';
import escapeRegExp from 'escape-string-regexp';
import sortBy from 'sort-by';
import scriptLoader from 'react-async-script-loader';
import ScriptjsLoader from 'react-script-loader';
const AnyReactComponent = ({ text }) => <div>{text }</div>;
let markers=[]

  let locations=[{

    location:{
    lat:36.416496,
    lng:25.4326204
    
    },
    title:'Museum of Prehistoric Thira',
    address:'Thera 847 00, Greece',
    
    },
    
    {
      location:{
        lat:36.419976,
        lng:25.4311383
        
        },
        title:'Archaeological Museum of Thera',
        address:'Erithrou Stavrou, Thira 847 00, Greece',
    
        },
    
        {
          location:{
        lat:36.396735,
        lng:25.46007
        
        },
        title:'Koutsoyannopoulos Wine Museum',
        address:'Epar. Od. Messarias - Archeas Thiras, Vothonas 847 00, Greece',
    
        },
    
    
        {
          location:{
        lat:36.4191673,
        lng:25.430705
        
        },
        title:'Santozeum',
        address:'Agiou Mina, Thira 847 00, Greece',
        showInfo: true,
    
        },
    
    
    {
        
      location:{
        lat:36.3804582,
        lng:25.4499444
        
        },
        title:'Santorini of the past',
        address:'Epar.Od. Pirgou Kallistis - Profiti Ilia, Pirgos Kallistis 847 00, Greece',
        showInfo: true,
    
    
        }];
  let infoBox=[];
  

  class App extends Component {
 
    constructor(props) {
      super(props);
    this.state = {
      infowindow:{},
      map:{},query:'' ,requestWasSuccessful:true,infowindows:[],clikedMarker:''}
    }
  
    componentWillReceiveProps({isScriptLoadSucceed}){
  if(isScriptLoadSucceed){
    var map=new  window.google.maps.Map(document.getElementById('map'),{
    zoom: 13,
center: new window.google.maps.LatLng(36.3931562,25.4615092)

});
this.setState({map:map});

console.log('Good');

  
}
else{
console.log('somthing went wrong');
this.setState({requestWasSuccessful:false})

}

for (var i = 0; i < locations.length; i++) {
  // Get the position from the location array.
  var position = locations[i].location;
  var title = locations[i].title;

let showMarkers=new window.google.maps.Marker({
  map: map,
          position: position,
          title:title,
          animation: window.google.maps.Animation.DROP,
id:i
        });
        var largeInfowindow = new window.google.maps.InfoWindow();
var bounds=new window.google.maps.LatLngBounds();
showMarkers.addListener('click',function(){
popinfoWindow(this,largeInfowindow);

})
markers.push(showMarkers)

}
function popinfoWindow(showMarkers ,infowindow){
if(infowindow.showMarkers!=showMarkers){
infowindow.showMarkers=showMarkers;
infowindow.setContent('<div>'+{title}+'</div>');
infowindow.open(map,showMarkers);
infowindow.addListener('closeclick',function(){
  infowindow.setMarker(null);})}


}

}


 render() {
   
   
      const {map, requestWasSuccessful} = this.state;
   
    
    return (
         requestWasSuccessful ?  (

 <div id="map container" role="application" tabIndex="-1">
          <title>Neighborhood Map</title>
          <div id="floating-panel">
      <input id="address" type="textbox" VALUE="Santorini, Gress"/>
      <input id="submit" type="button" value="Find"/>
      <div id="firstComponent">
      </div>
      <div id="secondComponent">
      </div> </div>
   
   <div id='map' role="application"> </div>
    </div>  
         ):(
           <h1>  Map </h1>  
  )
)
}
}


export default scriptLoader(
  [`https://maps.googleapis.com/maps/api/js?key=AIzaSyDjEkelTDgzC58cRUzhWys1Hu5WQ-B18JI&libraries=places`])(App);
