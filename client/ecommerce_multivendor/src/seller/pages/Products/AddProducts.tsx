import { AddPhotoAlternate, Close } from "@mui/icons-material";
import {
  Button,
  CircularProgress,
  FormControl,
  FormHelperText,
  Grid2,
  IconButton,
  InputLabel,
  MenuItem,
  Select,
  TextField,
} from "@mui/material";
import React, { useState } from "react";
import { uploadToCloudinary } from "../../../Utill/UploadToCloudnary";
import { useFormik } from "formik";
import { colors } from "../../../data/Category/filters/Color";

const categoryTwo: { [key: string]: any[] } = {
  //   men: menLeve1Two,
  // women: womenLeve1Two,
  // kids:[]
  // home_furniture: furnitureLeve1Two,
  // beauty: []
  // electronics: electronicsLeve1Two,
};
function AddProducts() {
  const [uploadImage, setUploadImage] = useState(false);
  const [snackbarOpen, setOpenSnackBar] = useState(false);

  const formik = useFormik({
    initialValues: {
      title: "",
      description: "",
      mrpPrice: "",
      sellingPrice: "",
      quantity: "",
      color: "",
      images: [],
      category: "",
      category2: "",
      category3: "",
      sizes: "",
    },
    // validationSchema: validationSchema,
    onSubmit: (values: any) => {
      console.log(values);
    },
  });

  const hand1eImageChange = async (event: any) => {
    const file = event.target.files[0];
    setUploadImage(true);
    const image = await uploadToCloudinary(file);
    setUploadImage(false);
  };

  const handleRemoveImage = (index: number) => {
    const updatedImages = [...formik.values.images];
    updatedImages.splice(index, 1);
    formik.setFieldValue("images", updatedImages);
  };

  const childCategory = (category: any, parentCategoryId: any) => {
    return category.filter((child: any) => {
      return child.parentCategoryId == parentCategoryId;
    });
  };

  return (
    <div>
      <form onSubmit={formik.handleSubmit} className="space-y-4 p-4">
        <Grid2 container spacing={2}>
          <Grid2 className="flex flex-wrap gap-5" size={{ xs: 12 }}>
            <input
              type="file"
              accept=" image/"
              id="filelnput"
              style={{ display: "none" }}
              onChange={hand1eImageChange}
            />

            <label className="relative" htmlFor="fileInput">
              <span
                className="w-24 h-24 cursor-pointer flex items-center
                justify-center p-3 border runded-md border-gray-700"
              >
                <AddPhotoAlternate className="text-gray-700" />
              </span>
              {uploadImage && (
                <div
                  className="absolute left-0 right-0 top-0 bottom-0 w-24 h-24 flex
                  justify-center items-center"
                >
                  <CircularProgress />
                </div>
              )}
            </label>

            <div className="flex flex-wrap gap-2">
              {formik.values.images.map((image: any, index: any) => (
                <div className="relative">
                  <img
                    className="w-24 h-24 object-cover"
                    key={index}
                    src={image}
                    alt={`ProductImage ${index + 1}`}
                  />
                  <IconButton
                    onClick={() => handleRemoveImage(index)}
                    className=""
                    size="small"
                    color="error"
                    sx={{
                      position: "absolute",
                      top: 0,
                      right: 0,
                      outline: "none",
                    }}
                  >
                    <Close sx={{ fontSize: "1rem" }} />
                  </IconButton>
                </div>
              ))}
            </div>
          </Grid2>
          <Grid2 size={{ xs: 12 }}>
            <TextField
              fullWidth
              id="title"
              name="title"
              label="Title"
              value={formik.values.title}
              onChange={formik.handleChange}
              error={formik.touched.title && Boolean(formik.errors.title)}
              helperText={
                formik.touched.title && typeof formik.errors.title === "string"
                  ? formik.errors.title
                  : ""
              }
              required
            />
          </Grid2>

          <Grid2 size={{ xs: 12 }}>
            <TextField
              fullWidth
              multiline
              rows={4}
              id="description"
              name="description"
              label="Description"
              value={formik.values.description}
              onChange={formik.handleChange}
              error={
                formik.touched.description && Boolean(formik.errors.description)
              }
              helperText={
                formik.touched.description &&
                typeof formik.errors.description === "string"
                  ? formik.errors.description
                  : ""
              }
              required
            />
          </Grid2>

          <Grid2 size={{ xs: 12, md: 4, lg: 3 }}>
            <TextField
              fullWidth
              id="mrpPrice"
              name="mrpPrice"
              label="MRP Price"
              type="number"
              value={formik.values.mrpPrice}
              onChange={formik.handleChange}
              error={formik.touched.mrpPrice && Boolean(formik.errors.mrpPrice)}
              helperText={
                formik.touched.mrpPrice &&
                typeof formik.errors.mrpPrice === "string"
                  ? formik.errors.mrpPrice
                  : ""
              }
              required
            />
          </Grid2>

          <Grid2 size={{ xs: 12, md: 4, lg: 3 }}>
            <TextField
              fullWidth
              id="sellingPrice"
              name="sellingPrice"
              label="Selling Price"
              type="number"
              value={formik.values.sellingPrice}
              onChange={formik.handleChange}
              error={
                formik.touched.sellingPrice &&
                Boolean(formik.errors.sellingPrice)
              }
              helperText={
                formik.touched.sellingPrice &&
                typeof formik.errors.sellingPrice === "string"
                  ? formik.errors.sellingPrice
                  : ""
              }
              required
            />
          </Grid2>

          {/* <Grid2 size={{ xs: 12, md: 4, lg: 3 }}>
            <TextField
              fullWidth
              id="quantity"
              name="quantity"
              label="Quantity"
              type="number"
              value={formik.values.quantity}
              onChange={formik.handleChange}
              error={formik.touched.quantity && Boolean(formik.errors.quantity)}
              helperText={
                formik.touched.quantity &&
                typeof formik.errors.quantity === "string"
                  ? formik.errors.quantity
                  : ""
              }
              required
            />
          </Grid2> */}

          <Grid2 size={{ xs: 12, md: 4, lg: 3 }}>
            <FormControl
              fullWidth
              error={formik.touched.color && Boolean(formik.errors.color)}
              required
            >
              <InputLabel id="color_label">Color</InputLabel>
              <Select
                labelId="color-label"
                id="color"
                name="colour"
                value={formik.values.color}
                onChange={formik.handleChange}
                label="Color"
              >
                <MenuItem value="">
                  <em>None</em>
                </MenuItem>
                {colors.map((color, index) => (
                  <MenuItem value={color.name}>
                    <div className="flex gap-3">
                      <span
                        style={{ background: color.hex }}
                        className={`h-5 w-5 rounded-full
                        ${color.name === "White" ? "border" : ""}`}
                      ></span>
                      <p>{color.name}</p>
                    </div>
                  </MenuItem>
                ))}
              </Select>
              {formik.touched.color &&
                formik.errors.color &&
                typeof formik.errors.color === "string" && (
                  <FormHelperText>{formik.errors.color}</FormHelperText>
                )}
            </FormControl>
          </Grid2>

          <Grid2 size={{ xs: 12, md: 4, lg: 3 }}>
            <FormControl
              fullWidth
              error={formik.touched.category && Boolean(formik.errors.category)}
              required
            >
              <InputLabel id="category_label">Color</InputLabel>
              <Select
                labelId="category-label"
                id="category"
                name="category"
                value={formik.values.category}
                onChange={formik.handleChange}
                label="Category"
              >
                <MenuItem value="">
                  <em>None</em>
                </MenuItem>
                {colors.map((category, index) => (
                  <MenuItem value={category.name}>
                    <div className="flex gap-3">
                      <span
                        style={{ background: category.hex }}
                        className={`h-5 w-5 rounded-full
                        ${category.name === "White" ? "border" : ""}`}
                      ></span>
                      <p>{category.name}</p>
                    </div>
                  </MenuItem>
                ))}
              </Select>
              {formik.touched.category &&
                formik.errors.category &&
                typeof formik.errors.category === "string" && (
                  <FormHelperText>{formik.errors.category}</FormHelperText>
                )}
            </FormControl>
          </Grid2>

          <Grid2 size={{ xs: 12, md: 4, lg: 4 }}>
            <TextField
              fullWidth
              id="category2"
              name="category2"
              label="Category 2"
              value={formik.values.category2}
              onChange={formik.handleChange}
              error={
                formik.touched.category2 && Boolean(formik.errors.category2)
              }
              helperText={
                formik.touched.category2 &&
                typeof formik.errors.category2 === "string"
                  ? formik.errors.category2
                  : ""
              }
            />
          </Grid2>

          <Grid2 size={{ xs: 12, md: 4, lg: 4 }}>
            <TextField
              fullWidth
              id="category3"
              name="category3"
              label="Category 3"
              value={formik.values.category3}
              onChange={formik.handleChange}
              error={
                formik.touched.category3 && Boolean(formik.errors.category3)
              }
              helperText={
                formik.touched.category3 &&
                typeof formik.errors.category3 === "string"
                  ? formik.errors.category3
                  : ""
              }
            />
          </Grid2>

          <Grid2 size={{ xs: 12, md: 4, lg: 4 }}>
            <TextField
              fullWidth
              id="sizes"
              name="sizes"
              label="Sizes"
              value={formik.values.sizes}
              onChange={formik.handleChange}
              error={formik.touched.sizes && Boolean(formik.errors.sizes)}
              helperText={
                formik.touched.sizes && typeof formik.errors.sizes === "string"
                  ? formik.errors.sizes
                  : ""
              }
            />
          </Grid2>
          <Grid2 size={{ xs: 12}}>
            <Button
            sx={{p:"14px"}}
            color="primary"
            variant="contained"
            fullWidth
            type="submit"
            >
              {false?<CircularProgress size="small"
              sx={{width:"27px" , height:"27px"}}/>
              :"Add Product"}
            </Button>
          </Grid2>
        </Grid2>
      </form>
    </div>
  );
}

export default AddProducts;
