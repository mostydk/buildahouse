//setTimeout needed to ensure elemental2 is loaded before main entrypoint is executed
setTimeout(function(){
	new BuildAHouseApp().onModuleLoad();
}, 0);