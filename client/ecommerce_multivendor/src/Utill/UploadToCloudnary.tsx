export const uploadToCloudinary = async(pics:any)=>{
    const cloud_name="dbp6lidfv"
    const upload_preset="Multi_Vendor"

    if(pics){
        const data= new FormData();
        data.append("file",pics);
        data.append("upload_preset",upload_preset);
        data.append("cloud_name", cloud_name);

        const res = await fetch("https://api.cloudinary.com/v1_1/dbp6lidfv/upload",{
            method:"POST",
            body:data
        })

        const fileData = await res.json();
        return fileData.url;
    }else{
        console.log("error:pics not found")
    }
}