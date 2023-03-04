import React from 'react';
import { Box, Typography, Button } from '@mui/material';
import { PsychologyOutlined } from '@mui/icons-material';
import Questions from '../Questions/Questions';

const styles = {
   root: {
      marginTop: "50px",
      textAlign: "center"
   },
   title: {
      fontSize: "3rem",
      color: "#424242"
   },
   headerIcon: {
      fontSize: "6rem",
      color: "#004d40"
   },
   resultText: {
      fontSize: "4rem",
      fontWeight: "bold",
      color: "#1b5e20"
   },
};

/**
 * Component to display Personality Welcome Screen
 */
const Personality = () => {
   const [mode, setMode] = React.useState("question");
   const [result, setResult] = React.useState("");

   React.useEffect(() => {

   }
   );

   const handleReset = () => {
      setMode("question");
      setResult("");
   }

   const handleMode = (mode) => {
      setMode(mode);
   }

   const createResult = (result) => {
      setResult(result);
   }

   return (
      <Box sx={styles.root}>
         <Box>
            <Typography sx={styles.title}>
               Personality Trait
            </Typography>
            <PsychologyOutlined sx={styles.headerIcon}/>
         </Box>
         <Box>
            {
               mode === "question" ? <Questions result={createResult} mode={handleMode}/> : 
               <Box>
                  <Typography sx={styles.resultText}>{result}</Typography>
                  <Button variant="outlined" onClick={handleReset}>Back to Questions</Button>
               </Box>
            }
         </Box>
      </Box>
   );
};

export default Personality;