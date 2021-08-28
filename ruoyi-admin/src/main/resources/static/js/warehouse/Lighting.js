// Models and loaders

let lampLoader = new THREE.GLTFLoader(); // Loader for Lamps

let lampSource = 'importedModels/factoryLights/scene.gltf'; // resource url
let onLoad = (gltf, position) => {  const model = gltf.scene.children[ 0 ];  model.position.copy( position );  model.scale.set(0.2,0.15,0.2);  model.rotation.x = Math.PI / 2;  model.rotation.z = Math.PI / 2;  scene.add(model);}; // called to load resource
let loadingBuffer = (timer) => { console.log((timer.loaded / timer.total * 100) + '% loaded') } // called while loading
let loaderError = (error) => { console.log('Error happened') } // When error is found

/* MAIN ROOM */

// Main room lamp positions
let lamp1Position = new THREE.Vector3(0,19,10);
let lamp2Position = new THREE.Vector3(0,19,20);
let lamp3Position = new THREE.Vector3(0,19,30);
let lamp4Position = new THREE.Vector3(0,19,40);
let lamp5Position = new THREE.Vector3(0,19,50);
let lamp6Position = new THREE.Vector3(0,19,-10);
let lamp7Position = new THREE.Vector3(0,19,0);

// Main room lamps
lampLoader.load(lampSource, gltf => onLoad(gltf, lamp1Position), loadingBuffer, loaderError);
lampLoader.load(lampSource, gltf => onLoad(gltf, lamp2Position), loadingBuffer, loaderError);
lampLoader.load(lampSource, gltf => onLoad(gltf, lamp3Position), loadingBuffer, loaderError);
lampLoader.load(lampSource, gltf => onLoad(gltf, lamp4Position), loadingBuffer, loaderError);
lampLoader.load(lampSource, gltf => onLoad(gltf, lamp5Position), loadingBuffer, loaderError);
lampLoader.load(lampSource, gltf => onLoad(gltf, lamp6Position), loadingBuffer, loaderError);
lampLoader.load(lampSource, gltf => onLoad(gltf, lamp7Position), loadingBuffer, loaderError);

/* Storage Room */

let onLoadSecondaryLamps = (gltf, position) => {  const model = gltf.scene.children[ 0 ];  model.position.copy( position );  model.scale.set(0.2,0.15,0.2);  model.rotation.x = Math.PI / 2;  scene.add(model);};

// Storage room lamp positions
let lamp8Position = new THREE.Vector3(-30,-1,40);
let lamp9Position = new THREE.Vector3(30,-1,40);

// Storage room lamps
lampLoader.load(lampSource, gltf => onLoadSecondaryLamps(gltf, lamp8Position), loadingBuffer, loaderError);
lampLoader.load(lampSource, gltf => onLoadSecondaryLamps(gltf, lamp9Position), loadingBuffer, loaderError);

/* LUZ e todas as funções necessárias */


/* FIM DA LUZ */
