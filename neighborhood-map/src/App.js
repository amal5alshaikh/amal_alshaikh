import React, { Component } from 'react';
import './App.css';
import fetchJsonp from 'fetch-jsonp';
import escapeRegExp from 'escape-string-regexp';
import sortBy from 'sort-by';
import scriptLoader from 'react-async-script-loader';
import ScriptjsLoader from 'react-script-loader';
import {locations} from './locations';
import LocationList from './LocationList';

const AnyReactComponent = ({ text }) => <div>{text }</div>;
let markers=[]

   let infoBox=[];
  

  class App extends Component {
 
    constructor(props) {
      super(props);
    this.state = {
      infowindow:{},locations:{},
      map:{},query:'' ,requestWasSuccessful:true,infowindows:[],clikedMarker:''}
    }
 
    updateQuery = (query) => {
        this.setState({ query: query.trim() })}
    

    componentWillReceiveProps({isScriptLoadSucceed}){
  if(isScriptLoadSucceed){
    var map=new  window.google.maps.Map(document.getElementById('map'),{
    zoom: 13,
center: new window.google.maps.LatLng(36.3921562,25.4615092)

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
          id:i,
          animation: window.google.maps.Animation.DROP,
        });
        var largeInfowindow = new window.google.maps.InfoWindow();

showMarkers.addListener('click',function(){
popinfoWindow(this,largeInfowindow);

})
//push the marker to an arry of showmarker after a loop
markers.push(showMarkers);
//extend the boundaaris of the map for the showmarker
}
function popinfoWindow(showMarkers ,infowindow){
if(infowindow.showMarkers!=showMarkers){
infowindow.showMarkers=showMarkers;
infowindow.setContent('<div>'+showMarkers.title+'</div>');
infowindow.open(map,showMarkers);
infowindow.addListener('closeclick',function(){
  infowindow.setMarker(null);})}


}



}

 render() {
   
   
      const {map, requestWasSuccessful} = this.state;
      let {showingm}= this.state;;
      if (this.state.query) {
          const match = new RegExp(escapeRegExp(this.state.query), 'i')
                showingm = locations.filter((locations) => match.test(locations.title))
        } else {
          showingm = locations
          
        }
    
    return (
         requestWasSuccessful ?  (

 <div id="map container" role="application" tabIndex="-1">
                   <title>Neighborhood Map</title>

                    <div id="floating-panel">
          <h4>Greess</h4>
               <input id="search" type="textbox" placeholder='Shearch Places' value={this.state.query} onChange={(event)=>this.updateQuery(event.target.value)} />
     
     <input id="buttom" type="button" value="Find"  />
      <ol className='list' >
  
        {showingm.map((locations)=>(
<ul key={locations.id}>
    {locations.title}
    </ul>))}
    </ol>
    

   </div> <div id='map' role="application"> 
     
         </div>  </div>


       
     ):(
           <h1>  Map </h1>  
  )
)
}
}


export default scriptLoader(
  [`https://maps.googleapis.com/maps/api/js?key=AIzaSyDjEkelTDgzC58cRUzhWys1Hu5WQ-B18JI&libraries=places`])(App);
markers.push(showMarkers);
//extend the boundaaris of the map for the showmarker
}
function popinfoWindow(showMarkers ,infowindow){
if(infowindow.showMarkers!=showMarkers){
infowindow.showMarkers=showMarkers;
infowindow.setContent('<div>'+showMarkers.title+'</div>');
infowindow.open(map,showMarkers);
infowindow.addListener('closeclick',function(){
  infowindow.setMarker(null);})}


}



}

 render() {
   
   
      const {map, requestWasSuccessful} = this.state;
      function showListing(){
        var bounds=new window.google.maps.LatLngBounds();
      for(var i=0;i<markers.length;i++){
      markers[i].setMap(map)
      bounds.extend(markers[i].position);
      }
      map.fitBounds(bounds);
      }
   function hideListings() {
  for (var i = 0; i < markers.length; i++) {
    markers[i].setMap(null);
  }}
    
    return (
         requestWasSuccessful ?  (

 <div id="map container" role="application" tabIndex="-1">
                   <title>Neighborhood Map</title>

          <div id="floating-panel">
          <h4>Greess</h4>
          <div>
      <input id="search" type="textbox"  />
      <input id="buttom" type="button" value="Find" onClick={()=>showListing()}/>
      <ol class='list'>
     { locations.map((locations)=>
      <ul >{locations.title}</ul>)}
      </ol>
      </div>
      </div>
   
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
