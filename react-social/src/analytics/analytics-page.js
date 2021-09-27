import React from "react";
import Box from '@material-ui/core/Box';
import Typography from '@material-ui/core/Typography';
import makeStyles from "@material-ui/core/styles/makeStyles";
import LineChart from "../components/LineChart";

const useStyles = makeStyles({
  root: {
    minWidth: 500,
    maxWidth: 1000,
    marginTop: 90,
    marginRight: 30,
  },
});

export default function AnalyticsPage() {
  const classes = useStyles();
  return <div>
    <Box className={classes.root}>
      <Typography h2>Analytics</Typography>
    </Box>
    <LineChart />
  </div>
}