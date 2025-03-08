import { DatePicker, LocalizationProvider } from "@mui/x-date-pickers";
import { Dayjs } from "dayjs";
import { useFormik } from "formik";
import React from "react";
import { AdapterDayjs } from "@mui/x-date-pickers/AdapterDayjs";
import { Box, Button, Grid2, TextField } from "@mui/material";

interface CouponFormValues {
  code: String;
  discountPrecentage: number;
  validityStartDate: Dayjs | null;
  validityEndDate: Dayjs | null;
  minimumOrderValue: number;
}

function AddNewCoupon() {
  const formik = useFormik<CouponFormValues>({
    initialValues: {
      code: "",
      discountPrecentage: 0,
      validityStartDate: null,
      validityEndDate: null,
      minimumOrderValue: 0,
    },
    onSubmit: (value) => {
      console.log("form submitted", value);
      const formattedValues = {
        ...value,
        validityStartDate: value.validityStartDate?.toISOString(),
        validityeNDDate: value.validityEndDate?.toISOString(),
      };
      console.log("formatted data", value, formattedValues);
    },
  });
  return (
    <div>
      <h1 className="text-2xl font-bold text-primary-color pb-5 text-center">
        Create New Coupon
      </h1>
      <LocalizationProvider dateAdapter={AdapterDayjs}>
        <Box component={"form"} onSubmit={formik.handleSubmit} sx={{ mt: 3 }}>
          <Grid2 container spacing={2}>
            <Grid2 size={{ xs: 12, sm: 6 }}>
              <TextField
                fullWidth
                name="code"
                label="Code"
                value={formik.values.code}
                onChange={formik.handleChange}
                error={formik.touched.code && Boolean(formik.errors.code)}
                helperText={formik.touched.code && typeof formik.errors.code === "string" ? formik.errors.code : ""}
                />
            </Grid2>
            <Grid2 size={{ xs: 12, sm: 6 }}>
              <TextField
                fullWidth
                name="discountPrecentage"
                label="Discount Precentage"
                value={formik.values.discountPrecentage}
                onChange={formik.handleChange}
                error={formik.touched.discountPrecentage && Boolean(formik.errors.discountPrecentage)}
                helperText={formik.touched.discountPrecentage && typeof formik.errors.discountPrecentage === "string" ? formik.errors.discountPrecentage : ""}
                />
            </Grid2>
            <Grid2 size={{ xs: 12, sm: 6 }}>
              <DatePicker
              sx={{width:"100%"}}
              label="Validity Start Date"
              name="validityStartDate"
              onChange={formik.handleChange}
              value={formik.values.validityStartDate}
              />
            </Grid2>
            <Grid2 size={{ xs: 12, sm: 6 }}>
              <DatePicker
              sx={{width:"100%"}}
              label="Validity End Date"
              name="validityEndDate"
              onChange={formik.handleChange}
              value={formik.values.validityEndDate}
              />
            </Grid2>
            <Grid2 size={{ xs: 12}}>
              <TextField
                fullWidth
                name="minimumOrderValue"
                label="Minimum OrderValue"
                value={formik.values.minimumOrderValue}
                onChange={formik.handleChange}
                error={formik.touched.minimumOrderValue && Boolean(formik.errors.minimumOrderValue)}
                helperText={formik.touched.minimumOrderValue && typeof formik.errors.minimumOrderValue === "string" ? formik.errors.minimumOrderValue : ""}
                />
            </Grid2>
            <Grid2 size={{xs:12}}>
              <Button variant="contained" fullWidth sx={{py:"0.8rem"}}>
                Create Coupon
              </Button>

            </Grid2>
          </Grid2>
        </Box>
      </LocalizationProvider>
    </div>
  );
}

export default AddNewCoupon;
