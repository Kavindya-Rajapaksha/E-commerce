import { Delete } from "@mui/icons-material";
import { Avatar, Box, Grid2, IconButton, Rating } from "@mui/material";
import React from "react";

function ReviewCard() {
  return (
    <div className="flex justify-between">
      <Grid2 container spacing={9}>
        <Grid2 size={{ xs: 1 }}>
          <Box>
            <Avatar
              className="text-white"
              sx={{ width: 56, height: 56, bgcolor: "#9155FS" }}
            >
              A
            </Avatar>
          </Box>
        </Grid2>
        <Grid2 size={{ xs: 9 }}>
          <div className="space-y-2">
            <div>
              <p className="font-semibold text-lg">Ashok</p>
              <p className="opacity-70">2022-6-5</p>
            </div>
          </div>
          <Rating readOnly value={4.5} precision={0.5} />
          <p>Value for product, greate prooduct</p>
          <div>
            <img
              className="w-24 h-24 object-cover"
              src="https://media.istockphoto.com/id/93355119/photo/indian-saris.jpg?s=612x612&w=0&k=20&c=afmfiTJg0VAmIY6P_TJ_JYsTfGhUdevv18WXQRUZ8NQ="
            />
          </div>
        </Grid2>
      </Grid2>
      <div>
      <IconButton>
        <Delete  sx={{color:"#ff5252"}}/>
      </IconButton>
      </div>
  
    </div>
  );
}

export default ReviewCard;
