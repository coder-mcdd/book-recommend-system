from fastapi import FastAPI, Query

app = FastAPI()

@app.get("/hello")
def read_root():
    return {"message": "Hello, World from FastAPI!"}

@app.get("/greet")
def greet(name: str = Query(None, title="Name", description="Enter your name")):
    return {"message": f"Hello, {name}!"}

@app.get("/item/{item_id}")
def read_item(item_id: int):
    return {"item_id": item_id}