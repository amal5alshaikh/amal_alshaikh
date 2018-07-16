import React, { Component } from 'react';
import './App.css';
import {locationInfo} from './locationInfo'
import fetchJsonp from 'fetch-jsonp';
import escapeRegExp from 'escape-string-regexp';
import sortBy from 'sort-by';
import scriptLoader from 'react-async-script-loader';
import ScriptjsLoader from 'react-script-loader';
const AnyReactComponent = ({ text }) => <div>{text }</div>;

  let markers=[];
  let infoBox=[];
  
  class App extends Component {
 
    constructor(props) {
      super(props);
    this.state = { map:{},query:'' ,requestWasSuccessful:true,locationInfo:[],clikedMarker:''}
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
let showMarkers=new window.google.maps.Marker({
  postion:locationInfo.location,
  map:map,
  animation: window.google.maps.Animation.DROP,
  title:locationInfo.title
})
markers.push(showMarkers)

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
   
   <div id='map' role="application"></div>
    </div>  
         ):(
           <h1>  Map </h1>  
  )
)
}
}


export default scriptLoader(
  [`https://maps.googleapis.com/maps/api/js?key=AIzaSyDjEkelTDgzC58cRUzhWys1Hu5WQ-B18JI&libraries=places`])(App);
