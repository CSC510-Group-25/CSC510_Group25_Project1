import React from "react";
import Box from '@material-ui/core/Box';
import Typography from '@material-ui/core/Typography';
import makeStyles from "@material-ui/core/styles/makeStyles";
import InventoryForm from "/inventory-form";
import InventoryTable from "/inventory-table";
import Grid from "@material-ui/core/Grid";

const useStyles = makeStyles({
  root: {
    minWidth: 500,
    maxWidth: 1000,
    marginTop: 90,
    marginRight: 30,
  },
});

export default function MenuPage() {
  const classes = useStyles();

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