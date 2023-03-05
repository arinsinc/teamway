FROM node:17-alpine3.12
WORKDIR /teamway-ui
ENV PATH /teamway-ui/node_modules/.bin:$PATH
COPY package.json .
COPY package-lock.json .
RUN npm install
COPY . .
EXPOSE 3000
CMD ["npm", "start"]