import React from 'react';
import axios from 'axios';
import { Box, Typography, Button, FormControl, FormLabel, 
   RadioGroup, FormControlLabel, Radio } from '@mui/material';

const styles = {
   root: {},
   header: {
      marginBottom: "20px",
      fontSize: "1.5rem",
      color: "#424242"
   },
   box: {
      display: "inline-grid",
   },
   questionBox: {
      margin: "20px",
      padding: "10px",
      boxShadow: 3,
   },
   button: {
      marginTop: "10px",
   },
   resetButton: {
      marginRight: "25px",
   },
};

/**
 * Component to display Questions
 */
const Questions = (props) => {
   const [questionList, setQuestionList] = React.useState([]);
   const [answerList, setAnswerList] = React.useState({});
   const addResult = props.result;
   const mode = props.mode;

   React.useEffect(() => {
      axios.get("http://localhost:8080/api/v1/questions")
      .then((res) => {
         setQuestionList(res.data.data);
      })
   }, []);

   const handleChange = (e, uid) => {
      answerList[uid] = e.target.value;
      setAnswerList(answerList);
   }

   const checkPersonality = (e) => {
      if (Object.keys(answerList).length !== questionList.length) {
         e.preventDefault();
         return;
      }
      axios.post("http://localhost:8080/api/v1/questions/check-personality", answerList)
      .then((res) => {
         addResult(res.data.data);
         mode("personality");
      });
   }

   const questionListItem = questionList.map((item) => {
      return <FormControl key={item.uid} sx={styles.questionBox}>
         <FormLabel>{item.question}</FormLabel>
         <RadioGroup row value={answerList[item.uid]}>
            {
               item.answers.map((answer) => {
                  return <FormControlLabel key={answer.uid} value={answer.answer} control={<Radio onClick={e => handleChange(e, item.uid)}/>} label={answer.answer}/>
               })
            }
         </RadioGroup>
      </FormControl>
   });

   return (
      <Box sx={styles.root}>
         <Box>
            <Typography sx={styles.header}>
               Questions to answer
            </Typography>
            <Box sx={styles.box}>
               { questionListItem }
            </Box>
         </Box>
         <Box>
            <Button variant="contained" onClick={e => checkPersonality(e)} sx={{mb: "25px"}}>Check your personality</Button>
         </Box>
      </Box>
   );
};

export default Questions;