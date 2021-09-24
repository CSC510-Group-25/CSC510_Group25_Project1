import React from "react";
import InventoryForm from "./inventory-form";
import InventoryTable from "./inventory-table";
import Grid from "@material-ui/core/Grid";

export default function InventoryPage() {

  return <div>
    <Grid
      container
      spacing={3}
      direction="column"
      alignItems="center"
      justifyContent="center"
      style={{ minHeight: '100vh' }}
    >
      <Grid item xs={3}>
        <InventoryForm/>
      </Grid>
      <Grid item md={12}>
        <InventoryTable/>
      </Grid>
    </Grid>
  </div>
}